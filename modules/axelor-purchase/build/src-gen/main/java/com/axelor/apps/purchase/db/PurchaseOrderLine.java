package com.axelor.apps.purchase.db;

import com.axelor.apps.account.db.TaxEquiv;
import com.axelor.apps.account.db.TaxLine;
import com.axelor.apps.base.db.Currency;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Unit;
import com.axelor.apps.base.interfaces.Currenciable;
import com.axelor.apps.base.interfaces.PricedOrderLine;
import com.axelor.apps.base.interfaces.ShippableOrderLine;
import com.axelor.apps.purchase.db.repo.PurchaseOrderLineListener;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(
  name = "PURCHASE_PURCHASE_ORDER_LINE",
  indexes = {
    @Index(
      columnList = "fullName"
    ),
    @Index(
      columnList = "purchase_order"
    ),
    @Index(
      columnList = "product"
    ),
    @Index(
      columnList = "tax_equiv"
    ),
    @Index(
      columnList = "unit"
    )
  }
)
@Track(
  fields = {
    @TrackField(
      name = "desiredReceiptDate"
    ),
    @TrackField(
      name = "estimatedReceiptDate"
    )
  }
)
@EntityListeners(PurchaseOrderLineListener.class)
public class PurchaseOrderLine extends AuditableModel implements PricedOrderLine, Currenciable, ShippableOrderLine {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_ORDER_LINE_SEQ"
  )
  private Long id;

  @NameColumn
  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String fullName;

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
    title = "Seq."
  )
  private Integer sequence = 0;

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
    title = "Qty"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal qty = new BigDecimal("1");

  @Widget(
    title = "Displayed Product name"
  )
  @NotNull
  private String productName;

  @Widget(
    title = "Supplier code"
  )
  private String productCode;

  @Widget(
    title = "Unit price W.T."
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal price = BigDecimal.ZERO;

  @Widget(
    title = "Unit price A.T.I."
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal inTaxPrice = BigDecimal.ZERO;

  @Widget(
    title = "Unit price discounted"
  )
  @Digits(
    integer = 10,
    fraction = 20
  )
  private BigDecimal priceDiscounted = BigDecimal.ZERO;

  @Widget(
    title = "Taxes"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<TaxLine> taxLineSet;

  @Widget(
    title = "Total W.T."
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal exTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Total A.T.I."
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal inTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Tax Equiv"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TaxEquiv taxEquiv;

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
    title = "Discount amount"
  )
  @Digits(
    integer = 10,
    fraction = 20
  )
  private BigDecimal discountAmount = BigDecimal.ZERO;

  @Widget(
    title = "Discount type",
    selection = "base.price.list.line.amount.type.select"
  )
  private Integer discountTypeSelect = 0;

  @Widget(
    title = "Ordered"
  )
  private Boolean isOrdered = Boolean.FALSE;

  @Widget(
    title = "Max purchase price"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal maxPurchasePrice = BigDecimal.ZERO;

  @Widget(
    title = "Desired receipt date"
  )
  private LocalDate desiredReceiptDate;

  @Widget(
    title = "Estim. receipt date"
  )
  private LocalDate estimatedReceiptDate;

  @Widget(
    title = "Received quantity"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal receivedQty = BigDecimal.ZERO;

  @Widget(
    title = "Supplier Comment"
  )
  private String supplierComment;

  @Widget(
    title = "Fixed Assets"
  )
  private Boolean fixedAssets = Boolean.FALSE;

  @Widget(
    hidden = true,
    title = "Total W.T. in company currency"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal companyExTaxTotal = BigDecimal.ZERO;

  @Widget(
    hidden = true,
    title = "Total A.T.I. in company currency"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal companyInTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Title Line"
  )
  private Boolean isTitleLine = Boolean.FALSE;

  @Widget(
    title = "Freeze fields"
  )
  @Transient
  private Boolean enableFreezeFields = Boolean.FALSE;

  @VirtualColumn
  @Transient
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency currency;

  @VirtualColumn
  @Transient
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency companyCurrency;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public PurchaseOrderLine() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    try {
      fullName = computeFullName();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getFullName()", e);
    }
    return fullName;
  }

  protected String computeFullName() {
    String fullName = "";
    	  		if(purchaseOrder != null && purchaseOrder.getPurchaseOrderSeq() != null){
    	  			fullName += purchaseOrder.getPurchaseOrderSeq();
    	  		}
    	  		if(productName != null)  {
    	  			fullName += "-";
    	  			if(productName.length() > 100)  {
    	  				fullName += productName.substring(1, 100);
    	  			}
    	  			else  {  fullName += productName;  }
    	  		}
    	  		return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Integer getSequence() {
    return sequence == null ? 0 : sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public BigDecimal getQty() {
    return qty == null ? BigDecimal.ZERO : qty;
  }

  public void setQty(BigDecimal qty) {
    this.qty = qty;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public BigDecimal getPrice() {
    return price == null ? BigDecimal.ZERO : price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getInTaxPrice() {
    return inTaxPrice == null ? BigDecimal.ZERO : inTaxPrice;
  }

  public void setInTaxPrice(BigDecimal inTaxPrice) {
    this.inTaxPrice = inTaxPrice;
  }

  public BigDecimal getPriceDiscounted() {
    return priceDiscounted == null ? BigDecimal.ZERO : priceDiscounted;
  }

  public void setPriceDiscounted(BigDecimal priceDiscounted) {
    this.priceDiscounted = priceDiscounted;
  }

  public Set<TaxLine> getTaxLineSet() {
    return taxLineSet;
  }

  public void setTaxLineSet(Set<TaxLine> taxLineSet) {
    this.taxLineSet = taxLineSet;
  }

  /**
   * Add the given {@link TaxLine} item to the {@code taxLineSet} collection.
   *
   * @param item the item to add
   */
  public void addTaxLineSetItem(TaxLine item) {
    if (getTaxLineSet() == null) {
      setTaxLineSet(new HashSet<>());
    }
    getTaxLineSet().add(item);
  }

  /**
   * Remove the given {@link TaxLine} item from the {@code taxLineSet} collection.
   *
   * @param item the item to remove
   */
  public void removeTaxLineSetItem(TaxLine item) {
    if (getTaxLineSet() == null) {
      return;
    }
    getTaxLineSet().remove(item);
  }

  /**
   * Clear the {@code taxLineSet} collection.
   */
  public void clearTaxLineSet() {
    if (getTaxLineSet() != null) {
      getTaxLineSet().clear();
    }
  }

  public BigDecimal getExTaxTotal() {
    return exTaxTotal == null ? BigDecimal.ZERO : exTaxTotal;
  }

  public void setExTaxTotal(BigDecimal exTaxTotal) {
    this.exTaxTotal = exTaxTotal;
  }

  public BigDecimal getInTaxTotal() {
    return inTaxTotal == null ? BigDecimal.ZERO : inTaxTotal;
  }

  public void setInTaxTotal(BigDecimal inTaxTotal) {
    this.inTaxTotal = inTaxTotal;
  }

  public TaxEquiv getTaxEquiv() {
    return taxEquiv;
  }

  public void setTaxEquiv(TaxEquiv taxEquiv) {
    this.taxEquiv = taxEquiv;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public BigDecimal getDiscountAmount() {
    return discountAmount == null ? BigDecimal.ZERO : discountAmount;
  }

  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public Integer getDiscountTypeSelect() {
    return discountTypeSelect == null ? 0 : discountTypeSelect;
  }

  public void setDiscountTypeSelect(Integer discountTypeSelect) {
    this.discountTypeSelect = discountTypeSelect;
  }

  public Boolean getIsOrdered() {
    return isOrdered == null ? Boolean.FALSE : isOrdered;
  }

  public void setIsOrdered(Boolean isOrdered) {
    this.isOrdered = isOrdered;
  }

  public BigDecimal getMaxPurchasePrice() {
    return maxPurchasePrice == null ? BigDecimal.ZERO : maxPurchasePrice;
  }

  public void setMaxPurchasePrice(BigDecimal maxPurchasePrice) {
    this.maxPurchasePrice = maxPurchasePrice;
  }

  public LocalDate getDesiredReceiptDate() {
    return desiredReceiptDate;
  }

  public void setDesiredReceiptDate(LocalDate desiredReceiptDate) {
    this.desiredReceiptDate = desiredReceiptDate;
  }

  public LocalDate getEstimatedReceiptDate() {
    return estimatedReceiptDate;
  }

  public void setEstimatedReceiptDate(LocalDate estimatedReceiptDate) {
    this.estimatedReceiptDate = estimatedReceiptDate;
  }

  public BigDecimal getReceivedQty() {
    return receivedQty == null ? BigDecimal.ZERO : receivedQty;
  }

  public void setReceivedQty(BigDecimal receivedQty) {
    this.receivedQty = receivedQty;
  }

  public String getSupplierComment() {
    return supplierComment;
  }

  public void setSupplierComment(String supplierComment) {
    this.supplierComment = supplierComment;
  }

  public Boolean getFixedAssets() {
    return fixedAssets == null ? Boolean.FALSE : fixedAssets;
  }

  public void setFixedAssets(Boolean fixedAssets) {
    this.fixedAssets = fixedAssets;
  }

  public BigDecimal getCompanyExTaxTotal() {
    return companyExTaxTotal == null ? BigDecimal.ZERO : companyExTaxTotal;
  }

  public void setCompanyExTaxTotal(BigDecimal companyExTaxTotal) {
    this.companyExTaxTotal = companyExTaxTotal;
  }

  public BigDecimal getCompanyInTaxTotal() {
    return companyInTaxTotal == null ? BigDecimal.ZERO : companyInTaxTotal;
  }

  public void setCompanyInTaxTotal(BigDecimal companyInTaxTotal) {
    this.companyInTaxTotal = companyInTaxTotal;
  }

  public Boolean getIsTitleLine() {
    return isTitleLine == null ? Boolean.FALSE : isTitleLine;
  }

  public void setIsTitleLine(Boolean isTitleLine) {
    this.isTitleLine = isTitleLine;
  }

  public Boolean getEnableFreezeFields() {
    return enableFreezeFields == null ? Boolean.FALSE : enableFreezeFields;
  }

  public void setEnableFreezeFields(Boolean enableFreezeFields) {
    this.enableFreezeFields = enableFreezeFields;
  }

  public Currency getCurrency() {
    try {
      currency = computeCurrency();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getCurrency()", e);
    }
    return currency;
  }

  protected Currency computeCurrency() {
    return purchaseOrder != null ? purchaseOrder.getCurrency() : null;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Currency getCompanyCurrency() {
    try {
      companyCurrency = computeCompanyCurrency();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getCompanyCurrency()", e);
    }
    return companyCurrency;
  }

  protected Currency computeCompanyCurrency() {
    return purchaseOrder != null ? purchaseOrder.getCompanyCurrency() : null;
  }

  public void setCompanyCurrency(Currency companyCurrency) {
    this.companyCurrency = companyCurrency;
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
    if (!(obj instanceof PurchaseOrderLine)) return false;

    final PurchaseOrderLine other = (PurchaseOrderLine) obj;
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
      .add("sequence", getSequence())
      .add("qty", getQty())
      .add("productName", getProductName())
      .add("productCode", getProductCode())
      .add("price", getPrice())
      .add("inTaxPrice", getInTaxPrice())
      .add("priceDiscounted", getPriceDiscounted())
      .add("exTaxTotal", getExTaxTotal())
      .add("inTaxTotal", getInTaxTotal())
      .add("discountAmount", getDiscountAmount())
      .omitNullValues()
      .toString();
  }
}
