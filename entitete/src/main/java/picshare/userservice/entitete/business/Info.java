package picshare.userservice.entitete.business;

import java.util.List;

public class Info {
    private List clani;
    private String opis_projekta;
    private List mikrostoritve;
    private List github;
    private List travis;
    private List dockerhub;

    public List getClani() {
        return clani;
    }

    public void setClani(List clani) {
        this.clani = clani;
    }

    public String getOpis_projekta() {
        return opis_projekta;
    }

    public void setOpis_projekta(String opis_projekta) {
        this.opis_projekta = opis_projekta;
    }

    public List getMikrostoritve() {
        return mikrostoritve;
    }

    public void setMikrostoritve(List mikrostoritve) {
        this.mikrostoritve = mikrostoritve;
    }

    public List getGithub() {
        return github;
    }

    public void setGithub(List github) {
        this.github = github;
    }

    public List getTravis() {
        return travis;
    }

    public void setTravis(List travis) {
        this.travis = travis;
    }

    public List getDockerhub() {
        return dockerhub;
    }

    public void setDockerhub(List dockerhub) {
        this.dockerhub = dockerhub;
    }
}
