package vn.myclass.core.persistence.entity;


import javax.persistence.*;
import java.security.Timestamp;
import java.util.List;

/**
 * Created by trunght on 28/06/2017.
 */
@Entity
@Table(name="listenguideline")
public class ListenguidelineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer listenguidelineid;

    @Column(name = "title")
    private  String title;

    @Column(name = "image")
    private  String image;

    @Column(name = "content")
    private  String content;

    @Column(name = "createddate")
    private Timestamp createddate;


    @Column(name = "modifieddate")
    private Timestamp modifieddate;

    public Integer getListenguidelineid() {
        return listenguidelineid;
    }

    public void setListenguidelineid(Integer listenguidelineid) {
        this.listenguidelineid = listenguidelineid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    @OneToMany(mappedBy = "listenguidelineEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

}
