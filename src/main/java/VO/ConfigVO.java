package VO;

public class ConfigVO {

    private String zacksURI;
    private String marketBeatURI;
    private String zacksOutdir;
    private String marketbeatOutDir;
    private String backupDir;

    public String getZacksURI() {
        return zacksURI;
    }

    public void setZacksURI(String zacksURI) {
        this.zacksURI = zacksURI;
    }

    public String getMarketBeatURI() {
        return marketBeatURI;
    }

    public void setMarketBeatURI(String marketBeatURI) {
        this.marketBeatURI = marketBeatURI;
    }

    public String getZacksOutdir() {
        return zacksOutdir;
    }

    public void setZacksOutdir(String zacksOutdir) {
        this.zacksOutdir = zacksOutdir;
    }

    public String getMarketbeatOutDir() {
        return marketbeatOutDir;
    }

    public void setMarketbeatOutDir(String marketbeatOutDir) {
        this.marketbeatOutDir = marketbeatOutDir;
    }

    public String getBackupDir() {
        return backupDir;
    }

    public void setBackupDir(String backupDir) {
        this.backupDir = backupDir;
    }
}
