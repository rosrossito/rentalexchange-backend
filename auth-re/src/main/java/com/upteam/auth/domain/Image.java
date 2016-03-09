package com.upteam.auth.domain;

import com.upteam.auth.domain.domainenum.Dtype;

import javax.persistence.*;

/**
 * Created by vnikolaev on 02.03.2016.
 */
@Entity
@Table(name = "image")
public class Image {

    @Id
    @SequenceGenerator(name = "imageseq", sequenceName = "image_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageseq")
    @Column(name = "id")
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "is_main")
    private boolean isMain;

    @Column(name = "is_selected")
    private boolean isSelected;

    @Column(name = "key")
    private Long key;

    @Column(name = "Dtype")
    @Enumerated(EnumType.STRING)
    private Dtype dType;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Dtype getdType() {
        return dType;
    }

    public void setdType(Dtype dType) {
        this.dType = dType;
    }

}
