package vn.myclass.core.persistence.entity;


import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by trunght on 28/06/2017.
 */
@Entity
@Table(name="comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid;

    @Column(name = "content")
    private String content;


    @Column(name = "createddate")
    private Timestamp createddate;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenguidelineEntity getListenguidelineEntity() {
        return listenguidelineEntity;
    }

    public void setListenguidelineEntity(ListenguidelineEntity listenguidelineEntity) {
        this.listenguidelineEntity = listenguidelineEntity;
    }

    @ManyToOne
    @JoinColumn(name = "listenguidelineid")
    private ListenguidelineEntity listenguidelineEntity;

}
