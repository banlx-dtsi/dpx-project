package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Company;
import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.TradingName;
import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.User;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "PURCHASE_PURCHASE_REQUEST",
  uniqueConstraints = @UniqueConstraint(
    columnNames = "purchaseRequestSeq"
  ),
  indexes = {
    @Index(
      columnList = "company"
    ),
    @Index(
      columnList = "supplier_partner"
    ),
    @Index(
      columnList = "purchase_order"
    ),
    @Index(
      columnList = "requester_user"
    ),
    @Index(
      columnList = "validator_user"
    ),
    @Index(
      columnList = "trading_name"
    )
  }
)
@Track(
  fields = @TrackField(
    name = "supplierPartner"
  )
)
public class PurchaseRequest extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_REQUEST_SEQ"
  )
  private Long id;

  @Widget(
    title = "Company"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company company;

  @Widget(
    title = "Supplier"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner supplierPartner;

  @Widget(
    title = "Description"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String description;

  @Widget(
    title = "Purchase order",
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PurchaseOrder purchaseOrder;

  @Widget(
    title = "Status",
    selection = "purchase.request.status.select"
  )
  private Integer statusSelect = 1;

  @Widget(
    title = "Ref."
  )
  private String purchaseRequestSeq;

  @Widget(
    title = "Purchase Request Lines"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "purchaseRequest",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PurchaseRequestLine> purchaseRequestLineList;

  @Widget(
    title = "Requester",
    readonly = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private User requesterUser;

  @Widget(
    title = "Validator",
    readonly = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private User validatorUser;

  @Widget(
    title = "Trading name"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TradingName tradingName;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public PurchaseRequest() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Partner getSupplierPartner() {
    return supplierPartner;
  }

  public void setSupplierPartner(Partner supplierPartner) {
    this.supplierPartner = supplierPartner;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Integer getStatusSelect() {
    return statusSelect == null ? 0 : statusSelect;
  }

  public void setStatusSelect(Integer statusSelect) {
    this.statusSelect = statusSelect;
  }

  public String getPurchaseRequestSeq() {
    return purchaseRequestSeq;
  }

  public void setPurchaseRequestSeq(String purchaseRequestSeq) {
    this.purchaseRequestSeq = purchaseRequestSeq;
  }

  public List<PurchaseRequestLine> getPurchaseRequestLineList() {
    return purchaseRequestLineList;
  }

  public void setPurchaseRequestLineList(List<PurchaseRequestLine> purchaseRequestLineList) {
    this.purchaseRequestLineList = purchaseRequestLineList;
  }

  /**
   * Add the given {@link PurchaseRequestLine} item to the {@code purchaseRequestLineList} collection.
   *
   * <p>
   * It sets {@code item.purchaseRequest = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPurchaseRequestLineListItem(PurchaseRequestLine item) {
    if (getPurchaseRequestLineList() == null) {
      setPurchaseRequestLineList(new ArrayList<>());
    }
    getPurchaseRequestLineList().add(item);
    item.setPurchaseRequest(this);
  }

  /**
   * Remove the given {@link PurchaseRequestLine} item from the {@code purchaseRequestLineList} collection.
   *
   * @param item the item to remove
   */
  public void removePurchaseRequestLineListItem(PurchaseRequestLine item) {
    if (getPurchaseRequestLineList() == null) {
      return;
    }
    getPurchaseRequestLineList().remove(item);
  }

  /**
   * Clear the {@code purchaseRequestLineList} collection.
   */
  public void clearPurchaseRequestLineList() {
    if (getPurchaseRequestLineList() != null) {
      getPurchaseRequestLineList().clear();
    }
  }

  public User getRequesterUser() {
    return requesterUser;
  }

  public void setRequesterUser(User requesterUser) {
    this.requesterUser = requesterUser;
  }

  public User getValidatorUser() {
    return validatorUser;
  }

  public void setValidatorUser(User validatorUser) {
    this.validatorUser = validatorUser;
  }

  public TradingName getTradingName() {
    return tradingName;
  }

  public void setTradingName(TradingName tradingName) {
    this.tradingName = tradingName;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof PurchaseRequest)) return false;

    final PurchaseRequest other = (PurchaseRequest) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("statusSelect", getStatusSelect())
      .add("purchaseRequestSeq", getPurchaseRequestSeq())
      .omitNullValues()
      .toString();
  }
}
