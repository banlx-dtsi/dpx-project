package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Unit;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(
  name = "PURCHASE_CALL_TENDER_OFFER",
  indexes = {
    @Index(
      columnList = "callTenderOfferSeq"
    ),
    @Index(
      columnList = "call_tender_need"
    ),
    @Index(
      columnList = "call_tender_supplier"
    ),
    @Index(
      columnList = "supplier_partner"
    ),
    @Index(
      columnList = "product"
    ),
    @Index(
      columnList = "requested_unit"
    ),
    @Index(
      columnList = "proposed_unit"
    ),
    @Index(
      columnList = "call_tender"
    ),
    @Index(
      columnList = "offer_mail"
    )
  }
)
public class CallTenderOffer extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_CALL_TENDER_OFFER_SEQ"
  )
  private Long id;

  @Widget(
    title = "Sequence"
  )
  @NameColumn
  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String callTenderOfferSeq;

  @Widget(
    title = "Line Nbr."
  )
  private Integer counter = 0;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTenderNeed callTenderNeed;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTenderSupplier callTenderSupplier;

  @Widget(
    title = "Supplier partner"
  )
  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner supplierPartner;

  @Widget(
    title = "Product"
  )
  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Product product;

  @Widget(
    title = "Requested quantity"
  )
  @NotNull
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal requestedQty = BigDecimal.ZERO;

  @Widget(
    title = "Requested unit"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit requestedUnit;

  @Widget(
    title = "Requested date"
  )
  private LocalDate requestedDate;

  @Widget(
    title = "Proposed quantity"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal proposedQty = BigDecimal.ZERO;

  @Widget(
    title = "Proposed unit"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit proposedUnit;

  @Widget(
    title = "Proposed date"
  )
  private LocalDate proposedDate;

  @Widget(
    title = "Proposed unit price"
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal proposedPrice = BigDecimal.ZERO;

  @Widget(
    title = "Comment"
  )
  private String offerComment;

  @Widget(
    selection = "purchase.call.tender.offer.status.select"
  )
  private Integer statusSelect = 0;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTender callTender;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTenderMail offerMail;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public CallTenderOffer() {
  }

  public CallTenderOffer(Integer counter) {
    this.counter = counter;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getCallTenderOfferSeq() {
    try {
      callTenderOfferSeq = computeCallTenderOfferSeq();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getCallTenderOfferSeq()", e);
    }
    return callTenderOfferSeq;
  }

  protected String computeCallTenderOfferSeq() {
    if (this.getCallTender() != null) {
                return
                    String.format(
                        "%s-%04d",
                        this.getCallTender().getCallTenderSeq(), counter);
              }
              else {
    			return Integer.toString(counter);
    		 }
  }

  public void setCallTenderOfferSeq(String callTenderOfferSeq) {
    this.callTenderOfferSeq = callTenderOfferSeq;
  }

  public Integer getCounter() {
    return counter == null ? 0 : counter;
  }

  public void setCounter(Integer counter) {
    this.counter = counter;
  }

  public CallTenderNeed getCallTenderNeed() {
    return callTenderNeed;
  }

  public void setCallTenderNeed(CallTenderNeed callTenderNeed) {
    this.callTenderNeed = callTenderNeed;
  }

  public CallTenderSupplier getCallTenderSupplier() {
    return callTenderSupplier;
  }

  public void setCallTenderSupplier(CallTenderSupplier callTenderSupplier) {
    this.callTenderSupplier = callTenderSupplier;
  }

  public Partner getSupplierPartner() {
    return supplierPartner;
  }

  public void setSupplierPartner(Partner supplierPartner) {
    this.supplierPartner = supplierPartner;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public BigDecimal getRequestedQty() {
    return requestedQty == null ? BigDecimal.ZERO : requestedQty;
  }

  public void setRequestedQty(BigDecimal requestedQty) {
    this.requestedQty = requestedQty;
  }

  public Unit getRequestedUnit() {
    return requestedUnit;
  }

  public void setRequestedUnit(Unit requestedUnit) {
    this.requestedUnit = requestedUnit;
  }

  public LocalDate getRequestedDate() {
    return requestedDate;
  }

  public void setRequestedDate(LocalDate requestedDate) {
    this.requestedDate = requestedDate;
  }

  public BigDecimal getProposedQty() {
    return proposedQty == null ? BigDecimal.ZERO : proposedQty;
  }

  public void setProposedQty(BigDecimal proposedQty) {
    this.proposedQty = proposedQty;
  }

  public Unit getProposedUnit() {
    return proposedUnit;
  }

  public void setProposedUnit(Unit proposedUnit) {
    this.proposedUnit = proposedUnit;
  }

  public LocalDate getProposedDate() {
    return proposedDate;
  }

  public void setProposedDate(LocalDate proposedDate) {
    this.proposedDate = proposedDate;
  }

  public BigDecimal getProposedPrice() {
    return proposedPrice == null ? BigDecimal.ZERO : proposedPrice;
  }

  public void setProposedPrice(BigDecimal proposedPrice) {
    this.proposedPrice = proposedPrice;
  }

  public String getOfferComment() {
    return offerComment;
  }

  public void setOfferComment(String offerComment) {
    this.offerComment = offerComment;
  }

  public Integer getStatusSelect() {
    return statusSelect == null ? 0 : statusSelect;
  }

  public void setStatusSelect(Integer statusSelect) {
    this.statusSelect = statusSelect;
  }

  public CallTender getCallTender() {
    return callTender;
  }

  public void setCallTender(CallTender callTender) {
    this.callTender = callTender;
  }

  public CallTenderMail getOfferMail() {
    return offerMail;
  }

  public void setOfferMail(CallTenderMail offerMail) {
    this.offerMail = offerMail;
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
    if (!(obj instanceof CallTenderOffer)) return false;

    final CallTenderOffer other = (CallTenderOffer) obj;
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
      .add("counter", getCounter())
      .add("requestedQty", getRequestedQty())
      .add("requestedDate", getRequestedDate())
      .add("proposedQty", getProposedQty())
      .add("proposedDate", getProposedDate())
      .add("proposedPrice", getProposedPrice())
      .add("offerComment", getOfferComment())
      .add("statusSelect", getStatusSelect())
      .omitNullValues()
      .toString();
  }
}
