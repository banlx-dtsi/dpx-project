package com.axelor.apps.base.db;

import com.axelor.apps.account.db.AccountManagement;
import com.axelor.apps.purchase.db.SupplierCatalog;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.TrackMessage;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "BASE_PRODUCT",
  uniqueConstraints = @UniqueConstraint(
    columnNames = {
      "product",
      "company"
    }
  ),
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "picture"
    ),
    @Index(
      columnList = "product_category"
    ),
    @Index(
      columnList = "product_family"
    ),
    @Index(
      columnList = "unit"
    ),
    @Index(
      columnList = "sale_currency"
    ),
    @Index(
      columnList = "purchase_currency"
    ),
    @Index(
      columnList = "product_variant_config"
    ),
    @Index(
      columnList = "product_variant"
    ),
    @Index(
      columnList = "parent_product"
    ),
    @Index(
      columnList = "default_supplier_partner"
    ),
    @Index(
      columnList = "bar_code"
    ),
    @Index(
      columnList = "barcode_type_config"
    ),
    @Index(
      columnList = "fullName"
    ),
    @Index(
      columnList = "mass_unit"
    ),
    @Index(
      columnList = "length_unit"
    ),
    @Index(
      columnList = "safety_label"
    ),
    @Index(
      columnList = "purchases_unit"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = {
    @TrackField(
      name = "name"
    ),
    @TrackField(
      name = "code"
    ),
    @TrackField(
      name = "productCategory"
    ),
    @TrackField(
      name = "productFamily"
    ),
    @TrackField(
      name = "saleSupplySelect"
    ),
    @TrackField(
      name = "sellable"
    ),
    @TrackField(
      name = "salePrice"
    ),
    @TrackField(
      name = "saleCurrency"
    ),
    @TrackField(
      name = "unit"
    ),
    @TrackField(
      name = "startDate"
    ),
    @TrackField(
      name = "endDate"
    ),
    @TrackField(
      name = "purchasable"
    ),
    @TrackField(
      name = "purchasePrice"
    ),
    @TrackField(
      name = "defaultSupplierPartner"
    ),
    @TrackField(
      name = "purchaseCurrency"
    ),
    @TrackField(
      name = "supplierDeliveryTime"
    ),
    @TrackField(
      name = "costPrice"
    ),
    @TrackField(
      name = "managPriceCoef"
    ),
    @TrackField(
      name = "costTypeSelect"
    ),
    @TrackField(
      name = "purchasesUnit"
    )
  },
  messages = @TrackMessage(
    message = "Product updated",
    condition = "true",
    on = TrackEvent.UPDATE
  )
)
public class Product extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_PRODUCT_SEQ"
  )
  private Long id;

  @Widget(
    translatable = true,
    title = "Name"
  )
  private String name;

  @EqualsInclude
  @Widget(
    title = "Serial Nbr"
  )
  @Column(
    unique = true
  )
  private String serialNumber;

  @EqualsInclude
  @Widget(
    title = "Code"
  )
  @Column(
    unique = true
  )
  private String code;

  @Widget(
    translatable = true,
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
    title = "Internal description"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String internalDescription;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile picture;

  @Widget(
    title = "Product category",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private ProductCategory productCategory;

  @Widget(
    title = "Accounting family",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private ProductFamily productFamily;

  @Widget(
    title = "Unit",
    massUpdate = true
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
    title = "Sale supply default method on sale order",
    massUpdate = true,
    selection = "product.sale.supply.select"
  )
  private Integer saleSupplySelect = 0;

  @Widget(
    title = "Type",
    massUpdate = true,
    selection = "product.product.type.select"
  )
  private String productTypeSelect;

  @Widget(
    title = "Procurement method",
    selection = "product.procurement.method.select"
  )
  private String procurementMethodSelect;

  @Widget(
    title = "Prototype"
  )
  private Boolean isPrototype = Boolean.FALSE;

  @Widget(
    title = "Unrenewed"
  )
  private Boolean isUnrenewed = Boolean.FALSE;

  @Widget(
    title = "Product Subtype",
    selection = "product.sub.type.product.select"
  )
  private Integer productSubTypeSelect = 0;

  @Widget(
    title = "Inventory type",
    selection = "product.inventory.type.select"
  )
  private Integer inventoryTypeSelect = 0;

  @Widget(
    title = "Sale price W.T.",
    massUpdate = true
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal salePrice = BigDecimal.ZERO;

  @Widget(
    title = "Sale currency"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency saleCurrency;

  @Widget(
    title = "Purchase price W.T.",
    massUpdate = true
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal purchasePrice = BigDecimal.ZERO;

  @Widget(
    title = "Last purchase date"
  )
  private LocalDate lastPurchaseDate;

  @Widget(
    title = "Last purchase price W.T.",
    copyable = false
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal lastPurchasePrice = BigDecimal.ZERO;

  @Widget(
    title = "Purchase / Cost currency"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency purchaseCurrency;

  @Widget(
    title = "Update sale price from cost price"
  )
  private Boolean autoUpdateSalePrice = Boolean.FALSE;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PriceListLine> priceListLineList;

  @Widget(
    title = "Cost price",
    massUpdate = true
  )
  @DecimalMin("0")
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal costPrice = BigDecimal.ZERO;

  @Widget(
    title = "Management coef."
  )
  @DecimalMin("0")
  private BigDecimal managPriceCoef = BigDecimal.ZERO;

  @Widget(
    title = "Shipping Coef."
  )
  @DecimalMin("0")
  private BigDecimal shippingCoef = BigDecimal.ZERO;

  @Widget(
    title = "Define the shipping coef by partner"
  )
  private Boolean defShipCoefByPartner = Boolean.FALSE;

  @Widget(
    title = "Product launch Date"
  )
  private LocalDate startDate;

  @Widget(
    title = "Product pulled off market Date"
  )
  private LocalDate endDate;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private ProductVariantConfig productVariantConfig;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private ProductVariant productVariant;

  @Widget(
    title = "Parent product"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Product parentProduct;

  @Widget(
    title = "Is model"
  )
  private Boolean isModel = Boolean.FALSE;

  @Widget(
    title = "Manage prices for product variants"
  )
  private Boolean manageVariantPrice = Boolean.FALSE;

  @Widget(
    title = "Default supplier"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner defaultSupplierPartner;

  @Widget(
    title = "Accounts configuration"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<AccountManagement> accountManagementList;

  @Widget(
    title = "Version",
    selection = "base.product.version.select"
  )
  private Integer versionSelect = 0;

  @Widget(
    title = "Sellable"
  )
  private Boolean sellable = Boolean.TRUE;

  @Widget(
    title = "Purchasable"
  )
  private Boolean purchasable = Boolean.TRUE;

  @Widget(
    title = "In ATI"
  )
  private Boolean inAti = Boolean.FALSE;

  @Widget(
    title = "Cost type",
    selection = "base.product.cost.type.select"
  )
  private Integer costTypeSelect = 1;

  @Widget(
    title = "Supplier delivery time (days)"
  )
  @Min(0)
  private Integer supplierDeliveryTime = 0;

  @Widget(
    title = "Barcode"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile barCode;

  @Widget(
    title = "Barcode Type"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private BarcodeTypeConfig barcodeTypeConfig;

  @Widget(
    title = "Alternative barcodes"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<AlternativeBarcode> alternativeBarcodeList;

  @Widget(
    translatable = true,
    title = "Full name"
  )
  @NameColumn
  private String fullName;

  @Widget(
    title = "Unit of mass"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit massUnit;

  @Widget(
    title = "Gross mass"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal grossMass = BigDecimal.ZERO;

  @Widget(
    title = "Net mass"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal netMass = BigDecimal.ZERO;

  @Widget(
    title = "Unit of length"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit lengthUnit;

  @Widget(
    title = "Length"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal length = new BigDecimal("0");

  @Widget(
    title = "Width"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal width = new BigDecimal("0");

  @Widget(
    title = "Height"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal height = new BigDecimal("0");

  @Widget(
    title = "Diameter"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal diameter = BigDecimal.ZERO;

  @Widget(
    title = "Article volume"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal articleVolume = BigDecimal.ZERO;

  @Widget(
    title = "Economic manuf. qty"
  )
  @Digits(
    integer = 10,
    fraction = 10
  )
  private BigDecimal economicManufOrderQty = BigDecimal.ZERO;

  @Widget(
    title = "Is shipping costs product"
  )
  private Boolean isShippingCostsProduct = Boolean.FALSE;

  @Widget(
    title = "Multiple quantities"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "saleProduct",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OrderBy("multipleQty")
  private List<ProductMultipleQty> saleProductMultipleQtyList;

  @Widget(
    title = "Allow to force sales quantities"
  )
  private Boolean allowToForceSaleQty = Boolean.FALSE;

  @Widget(
    title = "Multiple quantities"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "purchaseProduct",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OrderBy("multipleQty")
  private List<ProductMultipleQty> purchaseProductMultipleQtyList;

  @Widget(
    title = "Allow to force purchases quantities"
  )
  private Boolean allowToForcePurchaseQty = Boolean.FALSE;

  @Widget(
    title = "Trading names that can sell this product"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<TradingName> tradingNameSellerSet;

  @Widget(
    title = "Trading names that can buy this product"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<TradingName> tradingNameBuyerSet;

  @Widget(
    title = "Products per companies"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<ProductCompany> productCompanyList;

  @Column(
    insertable = false,
    updatable = false
  )
  private String dtype;

  @Widget(
    title = "This product is a packaging ?"
  )
  private Boolean isPackaging = Boolean.FALSE;

  @Widget(
    title = "Inner length (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal innerLength = new BigDecimal("0");

  @Widget(
    title = "Inner width (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal innerWidth = new BigDecimal("0");

  @Widget(
    title = "Inner height (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal innerHeight = new BigDecimal("0");

  @Widget(
    title = "Outer length (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal outerLength = new BigDecimal("0");

  @Widget(
    title = "Outer width (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal outerWidth = new BigDecimal("0");

  @Widget(
    title = "Outer height (mm)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal outerHeight = new BigDecimal("0");

  @Widget(
    title = "Min acceptable weight (kg)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal minWeight = new BigDecimal("0");

  @Widget(
    title = "Max acceptable weight (kg)"
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal maxWeight = new BigDecimal("0");

  @Widget(
    title = "Packaging level",
    selection = "base.product.packaging.level.select"
  )
  private Integer packagingLevelSelect = 0;

  @Widget(
    title = "Fields"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String productAttrs;

  @Widget(
    title = "Expense"
  )
  private Boolean expense = Boolean.FALSE;

  @Widget(
    title = "Label"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile safetyLabel;

  @Widget(
    title = "CLP"
  )
  private Boolean isClp = Boolean.FALSE;

  @ManyToMany(
    fetch = FetchType.LAZY,
    mappedBy = "productSet",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Icpe> icpeSet;

  @ManyToMany(
    fetch = FetchType.LAZY,
    mappedBy = "productSet",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<HazardPictogram> hazardPictogramSet;

  @Widget(
    title = "Retention type",
    selection = "base.product.retention.type.select"
  )
  private Integer retentionTypeSelect = 0;

  @ManyToMany(
    fetch = FetchType.LAZY,
    mappedBy = "productSet",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<HazardPhrase> hazardPhraseSet;

  @Widget(
    title = "Purchases unit",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit purchasesUnit;

  @Widget(
    title = "Supplier Catalog Lines"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<SupplierCatalog> supplierCatalogList;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public Product() {
  }

  public Product(String name, String code, String description, String internalDescription, MetaFile picture, ProductCategory productCategory, ProductFamily productFamily, Unit unit, Integer saleSupplySelect, String productTypeSelect, String procurementMethodSelect, Currency saleCurrency, Currency purchaseCurrency, LocalDate startDate, LocalDate endDate) {
    this.name = name;
    this.code = code;
    this.description = description;
    this.internalDescription = internalDescription;
    this.picture = picture;
    this.productCategory = productCategory;
    this.productFamily = productFamily;
    this.unit = unit;
    this.saleSupplySelect = saleSupplySelect;
    this.productTypeSelect = productTypeSelect;
    this.procurementMethodSelect = procurementMethodSelect;
    this.saleCurrency = saleCurrency;
    this.purchaseCurrency = purchaseCurrency;
    this.startDate = startDate;
    this.endDate = endDate;
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

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInternalDescription() {
    return internalDescription;
  }

  public void setInternalDescription(String internalDescription) {
    this.internalDescription = internalDescription;
  }

  public MetaFile getPicture() {
    return picture;
  }

  public void setPicture(MetaFile picture) {
    this.picture = picture;
  }

  public ProductCategory getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(ProductCategory productCategory) {
    this.productCategory = productCategory;
  }

  public ProductFamily getProductFamily() {
    return productFamily;
  }

  public void setProductFamily(ProductFamily productFamily) {
    this.productFamily = productFamily;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public Integer getSaleSupplySelect() {
    return saleSupplySelect == null ? 0 : saleSupplySelect;
  }

  public void setSaleSupplySelect(Integer saleSupplySelect) {
    this.saleSupplySelect = saleSupplySelect;
  }

  public String getProductTypeSelect() {
    return productTypeSelect;
  }

  public void setProductTypeSelect(String productTypeSelect) {
    this.productTypeSelect = productTypeSelect;
  }

  public String getProcurementMethodSelect() {
    return procurementMethodSelect;
  }

  public void setProcurementMethodSelect(String procurementMethodSelect) {
    this.procurementMethodSelect = procurementMethodSelect;
  }

  public Boolean getIsPrototype() {
    return isPrototype == null ? Boolean.FALSE : isPrototype;
  }

  public void setIsPrototype(Boolean isPrototype) {
    this.isPrototype = isPrototype;
  }

  public Boolean getIsUnrenewed() {
    return isUnrenewed == null ? Boolean.FALSE : isUnrenewed;
  }

  public void setIsUnrenewed(Boolean isUnrenewed) {
    this.isUnrenewed = isUnrenewed;
  }

  public Integer getProductSubTypeSelect() {
    return productSubTypeSelect == null ? 0 : productSubTypeSelect;
  }

  public void setProductSubTypeSelect(Integer productSubTypeSelect) {
    this.productSubTypeSelect = productSubTypeSelect;
  }

  public Integer getInventoryTypeSelect() {
    return inventoryTypeSelect == null ? 0 : inventoryTypeSelect;
  }

  public void setInventoryTypeSelect(Integer inventoryTypeSelect) {
    this.inventoryTypeSelect = inventoryTypeSelect;
  }

  public BigDecimal getSalePrice() {
    return salePrice == null ? BigDecimal.ZERO : salePrice;
  }

  public void setSalePrice(BigDecimal salePrice) {
    this.salePrice = salePrice;
  }

  public Currency getSaleCurrency() {
    return saleCurrency;
  }

  public void setSaleCurrency(Currency saleCurrency) {
    this.saleCurrency = saleCurrency;
  }

  public BigDecimal getPurchasePrice() {
    return purchasePrice == null ? BigDecimal.ZERO : purchasePrice;
  }

  public void setPurchasePrice(BigDecimal purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public LocalDate getLastPurchaseDate() {
    return lastPurchaseDate;
  }

  public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
    this.lastPurchaseDate = lastPurchaseDate;
  }

  public BigDecimal getLastPurchasePrice() {
    return lastPurchasePrice == null ? BigDecimal.ZERO : lastPurchasePrice;
  }

  public void setLastPurchasePrice(BigDecimal lastPurchasePrice) {
    this.lastPurchasePrice = lastPurchasePrice;
  }

  public Currency getPurchaseCurrency() {
    return purchaseCurrency;
  }

  public void setPurchaseCurrency(Currency purchaseCurrency) {
    this.purchaseCurrency = purchaseCurrency;
  }

  public Boolean getAutoUpdateSalePrice() {
    return autoUpdateSalePrice == null ? Boolean.FALSE : autoUpdateSalePrice;
  }

  public void setAutoUpdateSalePrice(Boolean autoUpdateSalePrice) {
    this.autoUpdateSalePrice = autoUpdateSalePrice;
  }

  public List<PriceListLine> getPriceListLineList() {
    return priceListLineList;
  }

  public void setPriceListLineList(List<PriceListLine> priceListLineList) {
    this.priceListLineList = priceListLineList;
  }

  /**
   * Add the given {@link PriceListLine} item to the {@code priceListLineList} collection.
   *
   * <p>
   * It sets {@code item.product = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPriceListLineListItem(PriceListLine item) {
    if (getPriceListLineList() == null) {
      setPriceListLineList(new ArrayList<>());
    }
    getPriceListLineList().add(item);
    item.setProduct(this);
  }

  /**
   * Remove the given {@link PriceListLine} item from the {@code priceListLineList} collection.
   *
   * @param item the item to remove
   */
  public void removePriceListLineListItem(PriceListLine item) {
    if (getPriceListLineList() == null) {
      return;
    }
    getPriceListLineList().remove(item);
  }

  /**
   * Clear the {@code priceListLineList} collection.
   */
  public void clearPriceListLineList() {
    if (getPriceListLineList() != null) {
      getPriceListLineList().clear();
    }
  }

  public BigDecimal getCostPrice() {
    return costPrice == null ? BigDecimal.ZERO : costPrice;
  }

  public void setCostPrice(BigDecimal costPrice) {
    this.costPrice = costPrice;
  }

  public BigDecimal getManagPriceCoef() {
    return managPriceCoef == null ? BigDecimal.ZERO : managPriceCoef;
  }

  public void setManagPriceCoef(BigDecimal managPriceCoef) {
    this.managPriceCoef = managPriceCoef;
  }

  public BigDecimal getShippingCoef() {
    return shippingCoef == null ? BigDecimal.ZERO : shippingCoef;
  }

  public void setShippingCoef(BigDecimal shippingCoef) {
    this.shippingCoef = shippingCoef;
  }

  public Boolean getDefShipCoefByPartner() {
    return defShipCoefByPartner == null ? Boolean.FALSE : defShipCoefByPartner;
  }

  public void setDefShipCoefByPartner(Boolean defShipCoefByPartner) {
    this.defShipCoefByPartner = defShipCoefByPartner;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public ProductVariantConfig getProductVariantConfig() {
    return productVariantConfig;
  }

  public void setProductVariantConfig(ProductVariantConfig productVariantConfig) {
    this.productVariantConfig = productVariantConfig;
  }

  public ProductVariant getProductVariant() {
    return productVariant;
  }

  public void setProductVariant(ProductVariant productVariant) {
    this.productVariant = productVariant;
  }

  public Product getParentProduct() {
    return parentProduct;
  }

  public void setParentProduct(Product parentProduct) {
    this.parentProduct = parentProduct;
  }

  public Boolean getIsModel() {
    return isModel == null ? Boolean.FALSE : isModel;
  }

  public void setIsModel(Boolean isModel) {
    this.isModel = isModel;
  }

  public Boolean getManageVariantPrice() {
    return manageVariantPrice == null ? Boolean.FALSE : manageVariantPrice;
  }

  public void setManageVariantPrice(Boolean manageVariantPrice) {
    this.manageVariantPrice = manageVariantPrice;
  }

  public Partner getDefaultSupplierPartner() {
    return defaultSupplierPartner;
  }

  public void setDefaultSupplierPartner(Partner defaultSupplierPartner) {
    this.defaultSupplierPartner = defaultSupplierPartner;
  }

  public List<AccountManagement> getAccountManagementList() {
    return accountManagementList;
  }

  public void setAccountManagementList(List<AccountManagement> accountManagementList) {
    this.accountManagementList = accountManagementList;
  }

  /**
   * Add the given {@link AccountManagement} item to the {@code accountManagementList} collection.
   *
   * <p>
   * It sets {@code item.product = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addAccountManagementListItem(AccountManagement item) {
    if (getAccountManagementList() == null) {
      setAccountManagementList(new ArrayList<>());
    }
    getAccountManagementList().add(item);
    item.setProduct(this);
  }

  /**
   * Remove the given {@link AccountManagement} item from the {@code accountManagementList} collection.
   *
   * @param item the item to remove
   */
  public void removeAccountManagementListItem(AccountManagement item) {
    if (getAccountManagementList() == null) {
      return;
    }
    getAccountManagementList().remove(item);
  }

  /**
   * Clear the {@code accountManagementList} collection.
   */
  public void clearAccountManagementList() {
    if (getAccountManagementList() != null) {
      getAccountManagementList().clear();
    }
  }

  public Integer getVersionSelect() {
    return versionSelect == null ? 0 : versionSelect;
  }

  public void setVersionSelect(Integer versionSelect) {
    this.versionSelect = versionSelect;
  }

  public Boolean getSellable() {
    return sellable == null ? Boolean.FALSE : sellable;
  }

  public void setSellable(Boolean sellable) {
    this.sellable = sellable;
  }

  public Boolean getPurchasable() {
    return purchasable == null ? Boolean.FALSE : purchasable;
  }

  public void setPurchasable(Boolean purchasable) {
    this.purchasable = purchasable;
  }

  public Boolean getInAti() {
    return inAti == null ? Boolean.FALSE : inAti;
  }

  public void setInAti(Boolean inAti) {
    this.inAti = inAti;
  }

  public Integer getCostTypeSelect() {
    return costTypeSelect == null ? 0 : costTypeSelect;
  }

  public void setCostTypeSelect(Integer costTypeSelect) {
    this.costTypeSelect = costTypeSelect;
  }

  public Integer getSupplierDeliveryTime() {
    return supplierDeliveryTime == null ? 0 : supplierDeliveryTime;
  }

  public void setSupplierDeliveryTime(Integer supplierDeliveryTime) {
    this.supplierDeliveryTime = supplierDeliveryTime;
  }

  public MetaFile getBarCode() {
    return barCode;
  }

  public void setBarCode(MetaFile barCode) {
    this.barCode = barCode;
  }

  public BarcodeTypeConfig getBarcodeTypeConfig() {
    return barcodeTypeConfig;
  }

  public void setBarcodeTypeConfig(BarcodeTypeConfig barcodeTypeConfig) {
    this.barcodeTypeConfig = barcodeTypeConfig;
  }

  public List<AlternativeBarcode> getAlternativeBarcodeList() {
    return alternativeBarcodeList;
  }

  public void setAlternativeBarcodeList(List<AlternativeBarcode> alternativeBarcodeList) {
    this.alternativeBarcodeList = alternativeBarcodeList;
  }

  /**
   * Add the given {@link AlternativeBarcode} item to the {@code alternativeBarcodeList} collection.
   *
   * <p>
   * It sets {@code item.product = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addAlternativeBarcodeListItem(AlternativeBarcode item) {
    if (getAlternativeBarcodeList() == null) {
      setAlternativeBarcodeList(new ArrayList<>());
    }
    getAlternativeBarcodeList().add(item);
    item.setProduct(this);
  }

  /**
   * Remove the given {@link AlternativeBarcode} item from the {@code alternativeBarcodeList} collection.
   *
   * @param item the item to remove
   */
  public void removeAlternativeBarcodeListItem(AlternativeBarcode item) {
    if (getAlternativeBarcodeList() == null) {
      return;
    }
    getAlternativeBarcodeList().remove(item);
  }

  /**
   * Clear the {@code alternativeBarcodeList} collection.
   */
  public void clearAlternativeBarcodeList() {
    if (getAlternativeBarcodeList() != null) {
      getAlternativeBarcodeList().clear();
    }
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Unit getMassUnit() {
    return massUnit;
  }

  public void setMassUnit(Unit massUnit) {
    this.massUnit = massUnit;
  }

  public BigDecimal getGrossMass() {
    return grossMass == null ? BigDecimal.ZERO : grossMass;
  }

  public void setGrossMass(BigDecimal grossMass) {
    this.grossMass = grossMass;
  }

  public BigDecimal getNetMass() {
    return netMass == null ? BigDecimal.ZERO : netMass;
  }

  public void setNetMass(BigDecimal netMass) {
    this.netMass = netMass;
  }

  public Unit getLengthUnit() {
    return lengthUnit;
  }

  public void setLengthUnit(Unit lengthUnit) {
    this.lengthUnit = lengthUnit;
  }

  public BigDecimal getLength() {
    return length == null ? BigDecimal.ZERO : length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public BigDecimal getWidth() {
    return width == null ? BigDecimal.ZERO : width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public BigDecimal getHeight() {
    return height == null ? BigDecimal.ZERO : height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  public BigDecimal getDiameter() {
    return diameter == null ? BigDecimal.ZERO : diameter;
  }

  public void setDiameter(BigDecimal diameter) {
    this.diameter = diameter;
  }

  public BigDecimal getArticleVolume() {
    return articleVolume == null ? BigDecimal.ZERO : articleVolume;
  }

  public void setArticleVolume(BigDecimal articleVolume) {
    this.articleVolume = articleVolume;
  }

  public BigDecimal getEconomicManufOrderQty() {
    return economicManufOrderQty == null ? BigDecimal.ZERO : economicManufOrderQty;
  }

  public void setEconomicManufOrderQty(BigDecimal economicManufOrderQty) {
    this.economicManufOrderQty = economicManufOrderQty;
  }

  public Boolean getIsShippingCostsProduct() {
    return isShippingCostsProduct == null ? Boolean.FALSE : isShippingCostsProduct;
  }

  public void setIsShippingCostsProduct(Boolean isShippingCostsProduct) {
    this.isShippingCostsProduct = isShippingCostsProduct;
  }

  public List<ProductMultipleQty> getSaleProductMultipleQtyList() {
    return saleProductMultipleQtyList;
  }

  public void setSaleProductMultipleQtyList(List<ProductMultipleQty> saleProductMultipleQtyList) {
    this.saleProductMultipleQtyList = saleProductMultipleQtyList;
  }

  /**
   * Add the given {@link ProductMultipleQty} item to the {@code saleProductMultipleQtyList} collection.
   *
   * <p>
   * It sets {@code item.saleProduct = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addSaleProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getSaleProductMultipleQtyList() == null) {
      setSaleProductMultipleQtyList(new ArrayList<>());
    }
    getSaleProductMultipleQtyList().add(item);
    item.setSaleProduct(this);
  }

  /**
   * Remove the given {@link ProductMultipleQty} item from the {@code saleProductMultipleQtyList} collection.
   *
   * @param item the item to remove
   */
  public void removeSaleProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getSaleProductMultipleQtyList() == null) {
      return;
    }
    getSaleProductMultipleQtyList().remove(item);
  }

  /**
   * Clear the {@code saleProductMultipleQtyList} collection.
   */
  public void clearSaleProductMultipleQtyList() {
    if (getSaleProductMultipleQtyList() != null) {
      getSaleProductMultipleQtyList().clear();
    }
  }

  public Boolean getAllowToForceSaleQty() {
    return allowToForceSaleQty == null ? Boolean.FALSE : allowToForceSaleQty;
  }

  public void setAllowToForceSaleQty(Boolean allowToForceSaleQty) {
    this.allowToForceSaleQty = allowToForceSaleQty;
  }

  public List<ProductMultipleQty> getPurchaseProductMultipleQtyList() {
    return purchaseProductMultipleQtyList;
  }

  public void setPurchaseProductMultipleQtyList(List<ProductMultipleQty> purchaseProductMultipleQtyList) {
    this.purchaseProductMultipleQtyList = purchaseProductMultipleQtyList;
  }

  /**
   * Add the given {@link ProductMultipleQty} item to the {@code purchaseProductMultipleQtyList} collection.
   *
   * <p>
   * It sets {@code item.purchaseProduct = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPurchaseProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getPurchaseProductMultipleQtyList() == null) {
      setPurchaseProductMultipleQtyList(new ArrayList<>());
    }
    getPurchaseProductMultipleQtyList().add(item);
    item.setPurchaseProduct(this);
  }

  /**
   * Remove the given {@link ProductMultipleQty} item from the {@code purchaseProductMultipleQtyList} collection.
   *
   * @param item the item to remove
   */
  public void removePurchaseProductMultipleQtyListItem(ProductMultipleQty item) {
    if (getPurchaseProductMultipleQtyList() == null) {
      return;
    }
    getPurchaseProductMultipleQtyList().remove(item);
  }

  /**
   * Clear the {@code purchaseProductMultipleQtyList} collection.
   */
  public void clearPurchaseProductMultipleQtyList() {
    if (getPurchaseProductMultipleQtyList() != null) {
      getPurchaseProductMultipleQtyList().clear();
    }
  }

  public Boolean getAllowToForcePurchaseQty() {
    return allowToForcePurchaseQty == null ? Boolean.FALSE : allowToForcePurchaseQty;
  }

  public void setAllowToForcePurchaseQty(Boolean allowToForcePurchaseQty) {
    this.allowToForcePurchaseQty = allowToForcePurchaseQty;
  }

  public Set<TradingName> getTradingNameSellerSet() {
    return tradingNameSellerSet;
  }

  public void setTradingNameSellerSet(Set<TradingName> tradingNameSellerSet) {
    this.tradingNameSellerSet = tradingNameSellerSet;
  }

  /**
   * Add the given {@link TradingName} item to the {@code tradingNameSellerSet} collection.
   *
   * @param item the item to add
   */
  public void addTradingNameSellerSetItem(TradingName item) {
    if (getTradingNameSellerSet() == null) {
      setTradingNameSellerSet(new HashSet<>());
    }
    getTradingNameSellerSet().add(item);
  }

  /**
   * Remove the given {@link TradingName} item from the {@code tradingNameSellerSet} collection.
   *
   * @param item the item to remove
   */
  public void removeTradingNameSellerSetItem(TradingName item) {
    if (getTradingNameSellerSet() == null) {
      return;
    }
    getTradingNameSellerSet().remove(item);
  }

  /**
   * Clear the {@code tradingNameSellerSet} collection.
   */
  public void clearTradingNameSellerSet() {
    if (getTradingNameSellerSet() != null) {
      getTradingNameSellerSet().clear();
    }
  }

  public Set<TradingName> getTradingNameBuyerSet() {
    return tradingNameBuyerSet;
  }

  public void setTradingNameBuyerSet(Set<TradingName> tradingNameBuyerSet) {
    this.tradingNameBuyerSet = tradingNameBuyerSet;
  }

  /**
   * Add the given {@link TradingName} item to the {@code tradingNameBuyerSet} collection.
   *
   * @param item the item to add
   */
  public void addTradingNameBuyerSetItem(TradingName item) {
    if (getTradingNameBuyerSet() == null) {
      setTradingNameBuyerSet(new HashSet<>());
    }
    getTradingNameBuyerSet().add(item);
  }

  /**
   * Remove the given {@link TradingName} item from the {@code tradingNameBuyerSet} collection.
   *
   * @param item the item to remove
   */
  public void removeTradingNameBuyerSetItem(TradingName item) {
    if (getTradingNameBuyerSet() == null) {
      return;
    }
    getTradingNameBuyerSet().remove(item);
  }

  /**
   * Clear the {@code tradingNameBuyerSet} collection.
   */
  public void clearTradingNameBuyerSet() {
    if (getTradingNameBuyerSet() != null) {
      getTradingNameBuyerSet().clear();
    }
  }

  public List<ProductCompany> getProductCompanyList() {
    return productCompanyList;
  }

  public void setProductCompanyList(List<ProductCompany> productCompanyList) {
    this.productCompanyList = productCompanyList;
  }

  /**
   * Add the given {@link ProductCompany} item to the {@code productCompanyList} collection.
   *
   * <p>
   * It sets {@code item.product = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addProductCompanyListItem(ProductCompany item) {
    if (getProductCompanyList() == null) {
      setProductCompanyList(new ArrayList<>());
    }
    getProductCompanyList().add(item);
    item.setProduct(this);
  }

  /**
   * Remove the given {@link ProductCompany} item from the {@code productCompanyList} collection.
   *
   * @param item the item to remove
   */
  public void removeProductCompanyListItem(ProductCompany item) {
    if (getProductCompanyList() == null) {
      return;
    }
    getProductCompanyList().remove(item);
  }

  /**
   * Clear the {@code productCompanyList} collection.
   */
  public void clearProductCompanyList() {
    if (getProductCompanyList() != null) {
      getProductCompanyList().clear();
    }
  }

  public String getDtype() {
    return dtype;
  }

  public void setDtype(String dtype) {
    this.dtype = dtype;
  }

  public Boolean getIsPackaging() {
    return isPackaging == null ? Boolean.FALSE : isPackaging;
  }

  public void setIsPackaging(Boolean isPackaging) {
    this.isPackaging = isPackaging;
  }

  public BigDecimal getInnerLength() {
    return innerLength == null ? BigDecimal.ZERO : innerLength;
  }

  public void setInnerLength(BigDecimal innerLength) {
    this.innerLength = innerLength;
  }

  public BigDecimal getInnerWidth() {
    return innerWidth == null ? BigDecimal.ZERO : innerWidth;
  }

  public void setInnerWidth(BigDecimal innerWidth) {
    this.innerWidth = innerWidth;
  }

  public BigDecimal getInnerHeight() {
    return innerHeight == null ? BigDecimal.ZERO : innerHeight;
  }

  public void setInnerHeight(BigDecimal innerHeight) {
    this.innerHeight = innerHeight;
  }

  public BigDecimal getOuterLength() {
    return outerLength == null ? BigDecimal.ZERO : outerLength;
  }

  public void setOuterLength(BigDecimal outerLength) {
    this.outerLength = outerLength;
  }

  public BigDecimal getOuterWidth() {
    return outerWidth == null ? BigDecimal.ZERO : outerWidth;
  }

  public void setOuterWidth(BigDecimal outerWidth) {
    this.outerWidth = outerWidth;
  }

  public BigDecimal getOuterHeight() {
    return outerHeight == null ? BigDecimal.ZERO : outerHeight;
  }

  public void setOuterHeight(BigDecimal outerHeight) {
    this.outerHeight = outerHeight;
  }

  public BigDecimal getMinWeight() {
    return minWeight == null ? BigDecimal.ZERO : minWeight;
  }

  public void setMinWeight(BigDecimal minWeight) {
    this.minWeight = minWeight;
  }

  public BigDecimal getMaxWeight() {
    return maxWeight == null ? BigDecimal.ZERO : maxWeight;
  }

  public void setMaxWeight(BigDecimal maxWeight) {
    this.maxWeight = maxWeight;
  }

  public Integer getPackagingLevelSelect() {
    return packagingLevelSelect == null ? 0 : packagingLevelSelect;
  }

  public void setPackagingLevelSelect(Integer packagingLevelSelect) {
    this.packagingLevelSelect = packagingLevelSelect;
  }

  public String getProductAttrs() {
    return productAttrs;
  }

  public void setProductAttrs(String productAttrs) {
    this.productAttrs = productAttrs;
  }

  public Boolean getExpense() {
    return expense == null ? Boolean.FALSE : expense;
  }

  public void setExpense(Boolean expense) {
    this.expense = expense;
  }

  public MetaFile getSafetyLabel() {
    return safetyLabel;
  }

  public void setSafetyLabel(MetaFile safetyLabel) {
    this.safetyLabel = safetyLabel;
  }

  public Boolean getIsClp() {
    return isClp == null ? Boolean.FALSE : isClp;
  }

  public void setIsClp(Boolean isClp) {
    this.isClp = isClp;
  }

  public Set<Icpe> getIcpeSet() {
    return icpeSet;
  }

  public void setIcpeSet(Set<Icpe> icpeSet) {
    this.icpeSet = icpeSet;
  }

  /**
   * Add the given {@link Icpe} item to the {@code icpeSet} collection.
   *
   * @param item the item to add
   */
  public void addIcpeSetItem(Icpe item) {
    if (getIcpeSet() == null) {
      setIcpeSet(new HashSet<>());
    }
    getIcpeSet().add(item);
  }

  /**
   * Remove the given {@link Icpe} item from the {@code icpeSet} collection.
   *
   * @param item the item to remove
   */
  public void removeIcpeSetItem(Icpe item) {
    if (getIcpeSet() == null) {
      return;
    }
    getIcpeSet().remove(item);
  }

  /**
   * Clear the {@code icpeSet} collection.
   */
  public void clearIcpeSet() {
    if (getIcpeSet() != null) {
      getIcpeSet().clear();
    }
  }

  public Set<HazardPictogram> getHazardPictogramSet() {
    return hazardPictogramSet;
  }

  public void setHazardPictogramSet(Set<HazardPictogram> hazardPictogramSet) {
    this.hazardPictogramSet = hazardPictogramSet;
  }

  /**
   * Add the given {@link HazardPictogram} item to the {@code hazardPictogramSet} collection.
   *
   * @param item the item to add
   */
  public void addHazardPictogramSetItem(HazardPictogram item) {
    if (getHazardPictogramSet() == null) {
      setHazardPictogramSet(new HashSet<>());
    }
    getHazardPictogramSet().add(item);
  }

  /**
   * Remove the given {@link HazardPictogram} item from the {@code hazardPictogramSet} collection.
   *
   * @param item the item to remove
   */
  public void removeHazardPictogramSetItem(HazardPictogram item) {
    if (getHazardPictogramSet() == null) {
      return;
    }
    getHazardPictogramSet().remove(item);
  }

  /**
   * Clear the {@code hazardPictogramSet} collection.
   */
  public void clearHazardPictogramSet() {
    if (getHazardPictogramSet() != null) {
      getHazardPictogramSet().clear();
    }
  }

  public Integer getRetentionTypeSelect() {
    return retentionTypeSelect == null ? 0 : retentionTypeSelect;
  }

  public void setRetentionTypeSelect(Integer retentionTypeSelect) {
    this.retentionTypeSelect = retentionTypeSelect;
  }

  public Set<HazardPhrase> getHazardPhraseSet() {
    return hazardPhraseSet;
  }

  public void setHazardPhraseSet(Set<HazardPhrase> hazardPhraseSet) {
    this.hazardPhraseSet = hazardPhraseSet;
  }

  /**
   * Add the given {@link HazardPhrase} item to the {@code hazardPhraseSet} collection.
   *
   * @param item the item to add
   */
  public void addHazardPhraseSetItem(HazardPhrase item) {
    if (getHazardPhraseSet() == null) {
      setHazardPhraseSet(new HashSet<>());
    }
    getHazardPhraseSet().add(item);
  }

  /**
   * Remove the given {@link HazardPhrase} item from the {@code hazardPhraseSet} collection.
   *
   * @param item the item to remove
   */
  public void removeHazardPhraseSetItem(HazardPhrase item) {
    if (getHazardPhraseSet() == null) {
      return;
    }
    getHazardPhraseSet().remove(item);
  }

  /**
   * Clear the {@code hazardPhraseSet} collection.
   */
  public void clearHazardPhraseSet() {
    if (getHazardPhraseSet() != null) {
      getHazardPhraseSet().clear();
    }
  }

  public Unit getPurchasesUnit() {
    return purchasesUnit;
  }

  public void setPurchasesUnit(Unit purchasesUnit) {
    this.purchasesUnit = purchasesUnit;
  }

  public List<SupplierCatalog> getSupplierCatalogList() {
    return supplierCatalogList;
  }

  public void setSupplierCatalogList(List<SupplierCatalog> supplierCatalogList) {
    this.supplierCatalogList = supplierCatalogList;
  }

  /**
   * Add the given {@link SupplierCatalog} item to the {@code supplierCatalogList} collection.
   *
   * <p>
   * It sets {@code item.product = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addSupplierCatalogListItem(SupplierCatalog item) {
    if (getSupplierCatalogList() == null) {
      setSupplierCatalogList(new ArrayList<>());
    }
    getSupplierCatalogList().add(item);
    item.setProduct(this);
  }

  /**
   * Remove the given {@link SupplierCatalog} item from the {@code supplierCatalogList} collection.
   *
   * @param item the item to remove
   */
  public void removeSupplierCatalogListItem(SupplierCatalog item) {
    if (getSupplierCatalogList() == null) {
      return;
    }
    getSupplierCatalogList().remove(item);
  }

  /**
   * Clear the {@code supplierCatalogList} collection.
   */
  public void clearSupplierCatalogList() {
    if (getSupplierCatalogList() != null) {
      getSupplierCatalogList().clear();
    }
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
    if (!(obj instanceof Product)) return false;

    final Product other = (Product) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getSerialNumber(), other.getSerialNumber())
      && Objects.equals(getCode(), other.getCode())
      && (getSerialNumber() != null
        || getCode() != null);
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
      .add("serialNumber", getSerialNumber())
      .add("code", getCode())
      .add("saleSupplySelect", getSaleSupplySelect())
      .add("productTypeSelect", getProductTypeSelect())
      .add("procurementMethodSelect", getProcurementMethodSelect())
      .add("isPrototype", getIsPrototype())
      .add("isUnrenewed", getIsUnrenewed())
      .add("productSubTypeSelect", getProductSubTypeSelect())
      .add("inventoryTypeSelect", getInventoryTypeSelect())
      .omitNullValues()
      .toString();
  }
}
