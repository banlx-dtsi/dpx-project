package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Unit;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_CALL_TENDER_NEED",
  indexes = {
    @Index(
      columnList = "product"
    ),
    @Index(
      columnList = "unit"
    ),
    @Index(
      columnList = "call_tender"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = {
    @TrackField(
      name = "requestedQty"
    ),
    @TrackField(
      name = "requestedDate"
    ),
    @TrackField(
      name = "typeSelect"
    )
  }
)
public class CallTenderNeed extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_CALL_TENDER_NEED_SEQ"
  )
  private Long id;

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
    title = "Quantity"
  )
  @NotNull
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal requestedQty = new BigDecimal("1");

  @Widget(
    title = "Unit"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit unit;

  @Widget(
    title = "Date"
  )
  private LocalDate requestedDate;

  @Widget(
    title = "Type",
    selection = "purchase.call.tender.need.type.select"
  )
  private Integer typeSelect = 0;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTender callTender;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public CallTenderNeed() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
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

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public LocalDate getRequestedDate() {
    return requestedDate;
  }

  public void setRequestedDate(LocalDate requestedDate) {
    this.requestedDate = requestedDate;
  }

  public Integer getTypeSelect() {
    return typeSelect == null ? 0 : typeSelect;
  }

  public void setTypeSelect(Integer typeSelect) {
    this.typeSelect = typeSelect;
  }

  public CallTender getCallTender() {
    return callTender;
  }

  public void setCallTender(CallTender callTender) {
    this.callTender = callTender;
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
    if (!(obj instanceof CallTenderNeed)) return false;

    final CallTenderNeed other = (CallTenderNeed) obj;
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
      .add("requestedQty", getRequestedQty())
      .add("requestedDate", getRequestedDate())
      .add("typeSelect", getTypeSelect())
      .omitNullValues()
      .toString();
  }
}
