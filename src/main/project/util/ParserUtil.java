package project.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ParserUtil {
    public String getPathToJsp(String jspName){
        return "/WEB-INF/pages/%s.jsp".formatted(jspName);
    }
    public String parsePathToUIID(String path){
        return path.split("/")[1];
    }

}

