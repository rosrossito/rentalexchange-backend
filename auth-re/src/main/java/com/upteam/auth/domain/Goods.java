package com.upteam.auth.domain;

import com.upteam.auth.domain.domainenum.PeriodType;
import com.upteam.auth.domain.domainenum.StatusType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by vnikolaev on 02.03.2016.
 */
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @SequenceGenerator(name = "goodsseq", sequenceName = "goods_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goodsseq")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "systemuser_id")
    private Long systemuserId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "article")
    private String article;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "deposit")
    private BigDecimal deposit;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "period")
    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    public Goods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSystemuserId() {
        return systemuserId;
    }

    public void setSystemuserId(Long systemuserId) {
        this.systemuserId = systemuserId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }
}
