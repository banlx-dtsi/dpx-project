package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.ProductMultipleQty;
import com.axelor.apps.base.db.ShippingCoef;
import com.axelor.apps.base.db.Unit;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_SUPPLIER_CATALOG",
  indexes = {
    @Index(
      columnList = "product"
    ),
    @Index(
      columnList = "supplier_partner"
    ),
    @Index(
      columnList = "unit"
    )
  }
)
public class SupplierCatalog extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_SUPPLIER_CATALOG_SEQ"
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
    title = "Supplier"
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
    title = "Product name on catalog"
  )
  private String productSupplierName;

  @Widget(
    title = "Product code on catalog"
  )
  private String productSupplierCode;

  @Widget(
    title = "Unit price"
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  @Column(
    nullable = true
  )
  private BigDecimal price;

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
    title = "Last update"
  )
  private LocalDate updateDate;

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
    title = "Calculated price/Qty"
  )
  private BigDecimal calculatedPrice = BigDecimal.ZERO;

  @Widget(
    title = "Shipping Coefficients"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "supplierCatalog",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<ShippingCoef> shippingCoefList;

  @Widget(
    title = "Multiple quantities"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "supplierCatalog",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OrderBy("multipleQty")
  private List<ProductMultipleQty> productMultipleQtyList;

  @Widget(
    title = "Take product multiple qty"
  )
  private Boolean isTakeProductMultipleQty = Boolean.FALSE;

  @Widget(
    title = "Quantity min"
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal minQty = BigDecimal.ZERO;

  @Widget(
    title = "Quantity max"
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal maxQty = BigDecimal.ZERO;

  @Widget(
    title = "Take product purchase price"
  )
  private Boolean isTakingProductPurchasePrice = Boolean.FALSE;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public SupplierCatalog() {
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

  public Partner getSupplierPartner() {
    return supplierPartner;
  }

  public void setSupplierPartner(Partner supplierPartner) {
    this.supplierPartner = supplierPartner;
  }

  public String getProductSupplierName() {
    return productSupplierName;
  }

  public void setProductSupplierName(String productSupplierName) {
    this.productSupplierName = productSupplierName;
  }

  public String getProductSupplierCode() {
    return productSupplierCode;
  }

  public void setProductSupplierCode(String productSupplierCode) {
    this.productSupplierCode = productSupplierCode;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public LocalDate getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getCalculatedPrice() {
    return calculatedPrice == null ? BigDecimal.ZERO : calculatedPrice;
  }

  public void setCalculatedPrice(BigDecimal calculatedPrice) {
    this.calculatedPrice = calculatedPrice;
  }

  public List<ShippingCoef> getShippingCoefList() {
    return shippingCoefList;
  }

  public void setShippingCoefList(List<ShippingCoef> shippingCoefList) {
    this.shippingCoefList = shippingCoefList;
  }

  /**
   * Add the given {@link ShippingCoef} item to the {@code shippingCoefList} collection.
   *
   * <p>
   * It sets {@code item.supplierCatalog = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addShippingCoefListItem(ShippingCoef item) {
    if (getShippingCoefList() == null) {
      setShippingCoefList(new ArrayList<>());
    }
    getShippingCoefList().add(item);
    item.setSupplierCatalog(this);
  }

  /**
   * Remove the given {@link ShippingCoef} item from the {@code shippingCoefList} collection.
   *
   * @param item the item to remove
   */
  public void removeShippingCoefListItem(ShippingCoef item) {
    if (getShippingCoefList() == null) {
      return;
    }
    getShippingCoefList().remove(item);
  }

  /**
   * Clear the {@code shippingCoefList} collection.
   */
  public void clearShippingCoefList() {
    if (getShippingCoefList() != null) {
      getShippingCoefList().clear();
    }
  }

  public List<ProductMultipleQty> getProductMultipleQtyList() {
    return productMultipleQtyList;
  }

  public void setProductMultipleQtyList(List<ProductMultipleQty> productMultipleQtyList) {
    this.productMultipleQtyList = productMultipleQtyList;
  }

  /**
   * Add the given {@link ProductMultipleQty} item to the {@code productMultipleQtyList} collection.
   *
   * <p>
   * It sets {@code item.supplierCatalog = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getProductMultipleQtyList() == null) {
      setProductMultipleQtyList(new ArrayList<>());
    }
    getProductMultipleQtyList().add(item);
    item.setSupplierCatalog(this);
  }

  /**
   * Remove the given {@link ProductMultipleQty} item from the {@code productMultipleQtyList} collection.
   *
   * @param item the item to remove
   */
  public void removeProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getProductMultipleQtyList() == null) {
      return;
    }
    getProductMultipleQtyList().remove(item);
  }

  /**
   * Clear the {@code productMultipleQtyList} collection.
   */
  public void clearProductMultipleQtyList() {
    if (getProductMultipleQtyList() != null) {
      getProductMultipleQtyList().clear();
    }
  }

  public Boolean getIsTakeProductMultipleQty() {
    return isTakeProductMultipleQty == null ? Boolean.FALSE : isTakeProductMultipleQty;
  }

  public void setIsTakeProductMultipleQty(Boolean isTakeProductMultipleQty) {
    this.isTakeProductMultipleQty = isTakeProductMultipleQty;
  }

  public BigDecimal getMinQty() {
    return minQty == null ? BigDecimal.ZERO : minQty;
  }

  public void setMinQty(BigDecimal minQty) {
    this.minQty = minQty;
  }

  public BigDecimal getMaxQty() {
    return maxQty == null ? BigDecimal.ZERO : maxQty;
  }

  public void setMaxQty(BigDecimal maxQty) {
    this.maxQty = maxQty;
  }

  public Boolean getIsTakingProductPurchasePrice() {
    return isTakingProductPurchasePrice == null ? Boolean.FALSE : isTakingProductPurchasePrice;
  }

  public void setIsTakingProductPurchasePrice(Boolean isTakingProductPurchasePrice) {
    this.isTakingProductPurchasePrice = isTakingProductPurchasePrice;
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
    if (!(obj instanceof SupplierCatalog)) return false;

    final SupplierCatalog other = (SupplierCatalog) obj;
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
      .add("productSupplierName", getProductSupplierName())
      .add("productSupplierCode", getProductSupplierCode())
      .add("price", getPrice())
      .add("updateDate", getUpdateDate())
      .add("calculatedPrice", getCalculatedPrice())
      .add("isTakeProductMultipleQty", getIsTakeProductMultipleQty())
      .add("minQty", getMinQty())
      .add("maxQty", getMaxQty())
      .add("isTakingProductPurchasePrice", getIsTakingProductPurchasePrice())
      .omitNullValues()
      .toString();
  }
}
