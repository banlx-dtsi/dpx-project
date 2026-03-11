package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Unit;
import com.axelor.auth.db.AuditableModel;
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_PURCHASE_REQUEST_LINE",
  indexes = {
    @Index(
      columnList = "product"
    ),
    @Index(
      columnList = "unit"
    ),
    @Index(
      columnList = "purchase_request"
    )
  }
)
public class PurchaseRequestLine extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_REQUEST_LINE_SEQ"
  )
  private Long id;

  @Widget(
    title = "Product"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Product product;

  @Widget(
    title = "New product"
  )
  private Boolean newProduct = Boolean.FALSE;

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
    title = "Quantity"
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal quantity = BigDecimal.ZERO;

  @Widget(
    title = "Product"
  )
  private String productTitle;

  @Widget(
    title = "Purchase request"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PurchaseRequest purchaseRequest;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public PurchaseRequestLine() {
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

  public Boolean getNewProduct() {
    return newProduct == null ? Boolean.FALSE : newProduct;
  }

  public void setNewProduct(Boolean newProduct) {
    this.newProduct = newProduct;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public BigDecimal getQuantity() {
    return quantity == null ? BigDecimal.ZERO : quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public String getProductTitle() {
    return productTitle;
  }

  public void setProductTitle(String productTitle) {
    this.productTitle = productTitle;
  }

  public PurchaseRequest getPurchaseRequest() {
    return purchaseRequest;
  }

  public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
    this.purchaseRequest = purchaseRequest;
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
    if (!(obj instanceof PurchaseRequestLine)) return false;

    final PurchaseRequestLine other = (PurchaseRequestLine) obj;
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
      .add("newProduct", getNewProduct())
      .add("quantity", getQuantity())
      .add("productTitle", getProductTitle())
      .omitNullValues()
      .toString();
  }
}
