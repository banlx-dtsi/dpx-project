package com.axelor.apps.purchase.db;

import com.axelor.apps.account.db.TaxLine;
import com.axelor.apps.account.db.TaxType;
import com.axelor.apps.base.interfaces.OrderLineTax;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Track;
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
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_PURCHASE_ORDER_LINE_TAX",
  indexes = {
    @Index(
      columnList = "purchase_order"
    ),
    @Index(
      columnList = "tax_line"
    ),
    @Index(
      columnList = "tax_type"
    )
  }
)
@Track(
  fields = {
    @TrackField(
      name = "exTaxBase"
    ),
    @TrackField(
      name = "taxTotal"
    ),
    @TrackField(
      name = "percentageTaxTotal"
    ),
    @TrackField(
      name = "inTaxTotal"
    )
  }
)
public class PurchaseOrderLineTax extends AuditableModel implements OrderLineTax {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_ORDER_LINE_TAX_SEQ"
  )
  private Long id;

  @Widget(
    title = "Purchase order"
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
    title = "Tax"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TaxLine taxLine;

  @Widget(
    title = "Base W.T."
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal exTaxBase = BigDecimal.ZERO;

  @Widget(
    title = "Amount Tax"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal taxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Amount Tax (via percentages)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal percentageTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Amount A.T.I."
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal inTaxTotal = BigDecimal.ZERO;

  private Boolean reverseCharged = Boolean.FALSE;

  @Widget(
    title = "Tax type"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TaxType taxType;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public PurchaseOrderLineTax() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public TaxLine getTaxLine() {
    return taxLine;
  }

  public void setTaxLine(TaxLine taxLine) {
    this.taxLine = taxLine;
  }

  public BigDecimal getExTaxBase() {
    return exTaxBase == null ? BigDecimal.ZERO : exTaxBase;
  }

  public void setExTaxBase(BigDecimal exTaxBase) {
    this.exTaxBase = exTaxBase;
  }

  public BigDecimal getTaxTotal() {
    return taxTotal == null ? BigDecimal.ZERO : taxTotal;
  }

  public void setTaxTotal(BigDecimal taxTotal) {
    this.taxTotal = taxTotal;
  }

  public BigDecimal getPercentageTaxTotal() {
    return percentageTaxTotal == null ? BigDecimal.ZERO : percentageTaxTotal;
  }

  public void setPercentageTaxTotal(BigDecimal percentageTaxTotal) {
    this.percentageTaxTotal = percentageTaxTotal;
  }

  public BigDecimal getInTaxTotal() {
    return inTaxTotal == null ? BigDecimal.ZERO : inTaxTotal;
  }

  public void setInTaxTotal(BigDecimal inTaxTotal) {
    this.inTaxTotal = inTaxTotal;
  }

  public Boolean getReverseCharged() {
    return reverseCharged == null ? Boolean.FALSE : reverseCharged;
  }

  public void setReverseCharged(Boolean reverseCharged) {
    this.reverseCharged = reverseCharged;
  }

  public TaxType getTaxType() {
    return taxType;
  }

  public void setTaxType(TaxType taxType) {
    this.taxType = taxType;
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
    if (!(obj instanceof PurchaseOrderLineTax)) return false;

    final PurchaseOrderLineTax other = (PurchaseOrderLineTax) obj;
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
      .add("exTaxBase", getExTaxBase())
      .add("taxTotal", getTaxTotal())
      .add("percentageTaxTotal", getPercentageTaxTotal())
      .add("inTaxTotal", getInTaxTotal())
      .add("reverseCharged", getReverseCharged())
      .omitNullValues()
      .toString();
  }
}
