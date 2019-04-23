/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.company;

import flatemi.api.DataFileAccessor;
import flatemi.com.core.StorageManager;
import java.io.File;

/**
 *
 * @author daan-
 */
public class CompanyFileAccessor implements DataFileAccessor {

    @Override
    public File getSourceFile() {
        return StorageManager.getCompanyListFile();
    }

    @Override
    public File getExcludeFile() {
        return StorageManager.getCompanyExclude();
    }
    
}
