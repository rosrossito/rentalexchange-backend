package com.upteam.auth.domain;

import com.upteam.auth.domain.domainenum.StatusType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by vnikolaev on 02.03.2016.
 */
@Entity
@Table(name = "statusstory")
public class StatusStory {

    @Id
    @SequenceGenerator(name = "statusstoryseq", sequenceName = "statusstory_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusstoryseq")
    private Long id;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "goods_systemuser_id")
    private Long goodsSystemuserId;

    @Column(name = "goods_category_id")
    private Long goodsCategoryId;

    @Column(name = "goods_status")
    @Enumerated(EnumType.STRING)
    private StatusType type;

    @Column(name = "effectivedate")
    private LocalDateTime effectiveDate;

    public StatusStory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsSystemuserId() {
        return goodsSystemuserId;
    }

    public void setGoodsSystemuserId(Long goodsSystemuserId) {
        this.goodsSystemuserId = goodsSystemuserId;
    }

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
