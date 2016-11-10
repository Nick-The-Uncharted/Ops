package top.kass.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "risk")
public class Risk {

    private int id;
    private int pid;
    private int type;
    private String content;
    private byte possibility;
    private byte impact;
    private String trigger;
    private int committer;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "possibility")
    public byte getPossibility() {
        return possibility;
    }

    public void setPossibility(byte possibility) {
        this.possibility = possibility;
    }

    @Basic
    @Column(name = "impact")
    public byte getImpact() {
        return impact;
    }

    public void setImpact(byte impact) {
        this.impact = impact;
    }

    @Basic
    @Column(name = "[trigger]")
    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    @Basic
    @Column(name = "committer")
    public int getCommitter() {
        return committer;
    }

    public void setCommitter(int committer) {
        this.committer = committer;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "[type]")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
