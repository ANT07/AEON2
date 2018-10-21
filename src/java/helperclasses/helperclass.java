/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helperclasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author anthony
 */
public class helperclass {

    private   ArrayList<String> paths = new ArrayList<>();

    public  void getPaths(ServletContext application,
            Set<String> paths) {
        if(paths != null){
            for (String string : paths) {
                Set<String> otherPaths = application.getResourcePaths(string);
                if(otherPaths.size() == 0){
                    int puntoJsp = string.indexOf(".jsp");
                    int puntoHtml = string.indexOf(".html");
                    if(puntoHtml != -1 || puntoJsp != -1){
                        this.paths.add(string);
                    }
                }
                else{
                    this.getPaths(application,
                            otherPaths);
                }
            }
        }
        


    }

//    public static String getPath(ServletContext application,
//            String path) {
//        String pathReturn = path;
//        Set<String> testPath = application.getResourcePaths(path);
//        if (testPath != null) {
//            for (String string : testPath) {
//                int puntoJsp = string.indexOf(".jsp");
//                int puntoHtml = string.indexOf(".html");
//                if (puntoJsp != -1 || puntoHtml != -1) {
//                    pathReturn += string;
//                } else {
//                    pathReturn += getPath(application,
//                            string);
//                }
//            }
//        }
//        else{
//            return "";
//        }
//        
//        return pathReturn;
//    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

}
