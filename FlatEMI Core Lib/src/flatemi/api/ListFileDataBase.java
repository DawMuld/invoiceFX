/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.api;

import flatemi.com.core.SessionLog;
import flatemi.com.pars.DateTimeParser;
import flatemi.com.pars.ExcludeParser;
import flatemi.com.pars.PrimeKeyParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 * @param <T>
 */
public class ListFileDataBase<T extends IndexedObject> implements FileDataBase<T> {

    private final LineParser<T> lineParser;
    private final EntryFactory<T> entryFactory;
    private ObservableList<T> liveItems;
    private final DataFileAccessor fileAccessor;

    public ListFileDataBase(LineParser<T> lineParser, EntryFactory<T> entryFactory, DataFileAccessor fileAccessor) {
        this.lineParser = lineParser;
        this.entryFactory = entryFactory;
        this.fileAccessor = fileAccessor;
        this.liveItems = null;

    }

    @Override
    public void update(T entry) {
        try {
            File source = fileAccessor.getSourceFile();
            File temp = new File("temp");
            try (BufferedReader reader = new BufferedReader(new FileReader(source)); PrintWriter writer = new PrintWriter(new FileWriter(temp))) {
                String line;
                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    int lineKey = PrimeKeyParser.keyFromLine(line);
                    if (lineKey == entry.getPrimaryKey()) {
                        entry.setLastMod(LocalDateTime.now());
                        String recLine = lineParser.toString(entry);
                        writer.println(recLine);
                    } else {
                        writer.println(line);
                    }
                }
                writer.flush();
            }
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\tntryclss=" + entry.getClass().getCanonicalName()
                    + "\n\tprimekey=" + String.valueOf(entry.getPrimaryKey())
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }
    }

    @Override
    public void remove(T entry) {
        File exclude = fileAccessor.getExcludeFile();
        if (!ExcludeParser.contains(exclude, entry.getPrimaryKey())) {
            try {
                try (PrintWriter writer = new PrintWriter(new FileWriter(exclude, true))) {
                    writer.print(String.valueOf(entry.getPrimaryKey()) + ";");
                    writer.flush();
                }

            } catch (Exception e) {
                SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                        + "\n\tfilepath=" + fileAccessor.getExcludeFile().getAbsolutePath()
                        + "\n\tntryclss=" + entry.getClass().getCanonicalName()
                        + "\n\tprimekey=" + String.valueOf(entry.getPrimaryKey())
                        + "\n\t__ErrMsg=" + e.getCause().getMessage());
            }
            if (liveItems != null) {
                liveItems.remove(entry);
            }
        }
    }

    @Override
    public T createNew() {
        int primeKey = 1;
        File file = fileAccessor.getSourceFile();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (line = reader.readLine(); null != line; line = reader.readLine()) {
                    int lineKey = PrimeKeyParser.keyFromLine(line);
                    if (lineKey != -1) {
                        primeKey = lineKey + 1;
                    }
                }
            }

        } catch (IOException e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\tprimekey=" + String.valueOf(primeKey)
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }

        T entry = entryFactory.createNew(primeKey);

        try {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                String line = lineParser.toString(entry);
                writer.println(line);
                writer.flush();
            }
        } catch (Exception e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\tntryclss=" + entry.getClass().getCanonicalName()
                    + "\n\tprimekey=" + String.valueOf(entry.getPrimaryKey())
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }

        if (liveItems != null) {
            liveItems.add(entry);
        }
        return entry;

    }

    @Override
    public T find(int primary_key) {
        T result = null;
        try {
            File file = fileAccessor.getSourceFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    int lineKey = PrimeKeyParser.keyFromLine(line);
                    if (lineKey == primary_key) {
                        result = lineParser.fromString(line);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\tprimekey=" + String.valueOf(primary_key)
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }
        return result;
    }

    @Override
    public List<T> collect(LineMatcher lineMatcher) {
        List<T> list = new ArrayList<>();
        File file = fileAccessor.getSourceFile();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (lineMatcher.collect(line)) {
                        list.add(lineParser.fromString(line));
                    }
                }
            }
        } catch (IOException e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\t_matcher=" + lineMatcher.getClass().getCanonicalName()
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }
        return list;
    }

    @Override
    public List<T> readAll() {
        List<T> list = new ArrayList<>();
        File file = fileAccessor.getSourceFile();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    list.add(lineParser.fromString(line));
                }
            }
        } catch (IOException e) {
            SessionLog.println(DateTimeParser.makeLogStamp() + ": Exception in list file database "
                    + "\n\tfilepath=" + fileAccessor.getSourceFile().getAbsolutePath()
                    + "\n\t__ErrMsg=" + e.getCause().getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<T> getItems() {
        if (liveItems == null) {
            liveItems = FXCollections.observableArrayList(readAll());
            Set<Integer> excludeList = ExcludeParser.parseSet(fileAccessor.getExcludeFile());
            for (int i = liveItems.size() - 1; i > -1; i--) {
                T entry = liveItems.get(i);
                if (excludeList.contains(entry.getPrimaryKey())) {
                    liveItems.remove(i);
                }
            }

        }
        return liveItems;
    }

    public String toLine(T entry) {
        return lineParser.toString(entry);
    }

}
