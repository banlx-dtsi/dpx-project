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
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "BASE_SHIPPING_COEF",
  indexes = {
    @Index(
      columnList = "company"
    ),
    @Index(
      columnList = "supplier_catalog"
    )
  }
)
public class ShippingCoef extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_SHIPPING_COEF_SEQ"
  )
  private Long id;

  @Widget(
    title = "Company"
  )
  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company company;

  @Widget(
    title = "Shipping Coef."
  )
  private BigDecimal shippingCoef = BigDecimal.ZERO;

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

  public ShippingCoef() {
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

  public BigDecimal getShippingCoef() {
    return shippingCoef == null ? BigDecimal.ZERO : shippingCoef;
  }

  public void setShippingCoef(BigDecimal shippingCoef) {
    this.shippingCoef = shippingCoef;
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
    if (!(obj instanceof ShippingCoef)) return false;

    final ShippingCoef other = (ShippingCoef) obj;
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
      .add("shippingCoef", getShippingCoef())
      .omitNullValues()
      .toString();
  }
}
