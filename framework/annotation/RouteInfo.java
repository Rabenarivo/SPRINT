package framework.annotation;

import java.lang.reflect.Method;

public class RouteInfo {
    private String nomClasse;
    private String nomMethode;
    private String url;
    private String type;
    private String returnType;

    public RouteInfo(String nomClasse, String nomMethode, String url, String type, String returnType) {
        this.nomClasse = nomClasse;
        this.nomMethode = nomMethode;
        this.url = url;
        this.type = type;
        this.returnType = returnType;
    }

    // Getters and Setters
    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNomMethode() {
        return nomMethode;
    }

    public void setNomMethode(String nomMethode) {
        this.nomMethode = nomMethode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return String.format(
            "RouteInfo{nomClasse='%s', nomMethode='%s', url='%s', type='%s', returnType='%s'}",
            nomClasse, nomMethode, url, type, returnType
        );
    }
}
