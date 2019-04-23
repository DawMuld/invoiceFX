/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.product;

import flatemi.api.DataFileAccessor;
import flatemi.com.core.StorageManager;
import java.io.File;

/**
 *
 * @author daan-
 */
public class ProductFileAccessor implements DataFileAccessor {

    @Override
    public File getSourceFile() {
        return StorageManager.getProductListFile();
    }

    @Override
    public File getExcludeFile() {
        return StorageManager.getProductExclude();
    }

}
