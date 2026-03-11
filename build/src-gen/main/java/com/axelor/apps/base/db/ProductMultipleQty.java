package com.axelor.apps.base.db;

import com.axelor.apps.purchase.db.SupplierCatalog;
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
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "BASE_PRODUCT_MULTIPLE_QTY",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "sale_product"
    ),
    @Index(
      columnList = "purchase_product"
    ),
    @Index(
      columnList = "supplier_catalog"
    )
  }
)
public class ProductMultipleQty extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_PRODUCT_MULTIPLE_QTY_SEQ"
  )
  private Long id;

  @Widget(
    title = "Name"
  )
  private String name;

  @Widget(
    title = "Multiple qty"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal multipleQty = BigDecimal.ZERO;

  @Widget(
    title = "Sale product"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Product saleProduct;

  @Widget(
    title = "Purchase product"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Product purchaseProduct;

  @Widget(
    title = "Supplier catalog"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private SupplierCatalog supplierCatalog;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public ProductMultipleQty() {
  }

  public ProductMultipleQty(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getMultipleQty() {
    return multipleQty == null ? BigDecimal.ZERO : multipleQty;
  }

  public void setMultipleQty(BigDecimal multipleQty) {
    this.multipleQty = multipleQty;
  }

  public Product getSaleProduct() {
    return saleProduct;
  }

  public void setSaleProduct(Product saleProduct) {
    this.saleProduct = saleProduct;
  }

  public Product getPurchaseProduct() {
    return purchaseProduct;
  }

  public void setPurchaseProduct(Product purchaseProduct) {
    this.purchaseProduct = purchaseProduct;
  }

  public SupplierCatalog getSupplierCatalog() {
    return supplierCatalog;
  }

  public void setSupplierCatalog(SupplierCatalog supplierCatalog) {
    this.supplierCatalog = supplierCatalog;
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
    if (!(obj instanceof ProductMultipleQty)) return false;

    final ProductMultipleQty other = (ProductMultipleQty) obj;
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
      .add("name", getName())
      .add("multipleQty", getMultipleQty())
      .omitNullValues()
      .toString();
  }
}
