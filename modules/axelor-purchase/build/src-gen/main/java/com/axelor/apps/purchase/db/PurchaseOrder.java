package com.axelor.apps.purchase.db;

import com.axelor.apps.account.db.FiscalPosition;
import com.axelor.apps.account.db.TaxNumber;
import com.axelor.apps.base.db.BankDetails;
import com.axelor.apps.base.db.Company;
import com.axelor.apps.base.db.Currency;
import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.PriceList;
import com.axelor.apps.base.db.PrintingSettings;
import com.axelor.apps.base.db.TradingName;
import com.axelor.apps.base.interfaces.Currenciable;
import com.axelor.apps.base.interfaces.PricedOrder;
import com.axelor.apps.base.interfaces.ShippableOrder;
import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.User;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.TrackMessage;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.team.db.Team;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
  name = "PURCHASE_PURCHASE_ORDER",
  uniqueConstraints = @UniqueConstraint(
    columnNames = {
      "purchaseOrderSeq",
      "company"
    }
  ),
  indexes = {
    @Index(
      columnList = "fullName"
    ),
    @Index(
      columnList = "company"
    ),
    @Index(
      columnList = "supplier_partner"
    ),
    @Index(
      columnList = "contact_partner"
    ),
    @Index(
      columnList = "price_list"
    ),
    @Index(
      columnList = "team"
    ),
    @Index(
      columnList = "buyer_user"
    ),
    @Index(
      columnList = "currency"
    ),
    @Index(
      columnList = "tax_number"
    ),
    @Index(
      columnList = "fiscal_position"
    ),
    @Index(
      columnList = "validated_by_user"
    ),
    @Index(
      columnList = "company_bank_details"
    ),
    @Index(
      columnList = "trading_name"
    ),
    @Index(
      columnList = "printing_settings"
    ),
    @Index(
      columnList = "call_tender"
    )
  }
)
@Track(
  fields = {
    @TrackField(
      name = "purchaseOrderSeq"
    ),
    @TrackField(
      name = "supplierPartner"
    ),
    @TrackField(
      name = "statusSelect",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "estimatedReceiptDate"
    )
  },
  messages = {
    @TrackMessage(
      message = "Purchase order created",
      condition = "true",
      on = TrackEvent.CREATE
    ),
    @TrackMessage(
      message = "Draft",
      condition = "statusSelect == 1",
      tag = "important"
    ),
    @TrackMessage(
      message = "Requested",
      condition = "statusSelect == 2",
      tag = "important"
    ),
    @TrackMessage(
      message = "Validated",
      condition = "statusSelect == 3",
      tag = "info"
    ),
    @TrackMessage(
      message = "Finished",
      condition = "statusSelect == 4",
      tag = "success"
    ),
    @TrackMessage(
      message = "Canceled",
      condition = "statusSelect == 5",
      tag = "warning"
    )
  }
)
public class PurchaseOrder extends AuditableModel implements PricedOrder, Currenciable, ShippableOrder {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_ORDER_SEQ"
  )
  private Long id;

  @NameColumn
  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String fullName;

  @Widget(
    title = "Ref.",
    readonly = true
  )
  private String purchaseOrderSeq;

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
    title = "Contact"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner contactPartner;

  @Widget(
    title = "Price list"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PriceList priceList;

  @Widget(
    title = "Team"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Team team;

  @Widget(
    title = "Buyer"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private User buyerUser;

  @Widget(
    title = "Currency"
  )
  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency currency;

  @Widget(
    title = "Estimated receipt date"
  )
  private LocalDate estimatedReceiptDate;

  @Widget(
    title = "Order Date"
  )
  private LocalDate orderDate;

  @Widget(
    title = "Products list"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "purchaseOrder",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PurchaseOrderLine> purchaseOrderLineList;

  @Widget(
    title = "Status",
    readonly = true,
    selection = "purchase.purchase.order.status.select"
  )
  private Integer statusSelect = 0;

  @Widget(
    title = "Supplier ref."
  )
  private String externalReference;

  @Widget(
    title = "Internal Ref."
  )
  private String internalReference;

  @Widget(
    title = "Receipt State",
    readonly = true,
    selection = "purchase.order.receipt.state"
  )
  private Integer receiptState = 1;

  @Widget(
    title = "Advance payment total",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal advanceTotal = BigDecimal.ZERO;

  @Widget(
    title = "Tax number"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TaxNumber taxNumber;

  @Widget(
    title = "Fiscal position"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private FiscalPosition fiscalPosition;

  @Widget(
    title = "Tax Lines"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "purchaseOrder",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PurchaseOrderLineTax> purchaseOrderLineTaxList;

  @Widget(
    title = "Validated by",
    readonly = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private User validatedByUser;

  @Widget(
    title = "Validation date",
    readonly = true
  )
  private LocalDateTime validationDateTime;

  @Widget(
    title = "Total W.T.",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal exTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Total Tax",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal taxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Total A.T.I.",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal inTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Amount to be spread over the timetable",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal amountToBeSpreadOverTheTimetable = BigDecimal.ZERO;

  @Widget(
    title = "Total W.T.",
    readonly = true
  )
  @Digits(
    integer = 17,
    fraction = 3
  )
  private BigDecimal companyExTaxTotal = BigDecimal.ZERO;

  @Widget(
    title = "Description To Display",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String notes;

  @Widget(
    title = "Internal Note",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String internalNote;

  @Widget(
    title = "Specific notes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String specificNotes;

  @Widget(
    title = "Version Number",
    readonly = true
  )
  private Integer versionNumber = 1;

  @Widget(
    title = "In ATI"
  )
  private Boolean inAti = Boolean.FALSE;

  @Widget(
    title = "Company bank"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private BankDetails companyBankDetails;

  @Widget(
    title = "Display price on requested purchase printing"
  )
  private Boolean displayPriceOnQuotationRequest = Boolean.FALSE;

  @Widget(
    title = "Message for requesting prices"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String priceRequest;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TradingName tradingName;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PrintingSettings printingSettings;

  @Widget(
    title = "Group products on printings"
  )
  private Boolean groupProductsOnPrintings = Boolean.FALSE;

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
    title = "Type",
    selection = "purchase.purchase.order.type.select"
  )
  private Integer typeSelect = 1;

  @Widget(
    title = "Order being edited"
  )
  private Boolean orderBeingEdited = Boolean.FALSE;

  @Widget(
    title = "Call for tenders"
  )
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

  public PurchaseOrder() {
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
    if(purchaseOrderSeq==null){
      			return " ";
      		}
      		return purchaseOrderSeq + "-" + supplierPartner.getName();
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPurchaseOrderSeq() {
    return purchaseOrderSeq;
  }

  public void setPurchaseOrderSeq(String purchaseOrderSeq) {
    this.purchaseOrderSeq = purchaseOrderSeq;
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

  public Partner getContactPartner() {
    return contactPartner;
  }

  public void setContactPartner(Partner contactPartner) {
    this.contactPartner = contactPartner;
  }

  public PriceList getPriceList() {
    return priceList;
  }

  public void setPriceList(PriceList priceList) {
    this.priceList = priceList;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public User getBuyerUser() {
    return buyerUser;
  }

  public void setBuyerUser(User buyerUser) {
    this.buyerUser = buyerUser;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public LocalDate getEstimatedReceiptDate() {
    return estimatedReceiptDate;
  }

  public void setEstimatedReceiptDate(LocalDate estimatedReceiptDate) {
    this.estimatedReceiptDate = estimatedReceiptDate;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public List<PurchaseOrderLine> getPurchaseOrderLineList() {
    return purchaseOrderLineList;
  }

  public void setPurchaseOrderLineList(List<PurchaseOrderLine> purchaseOrderLineList) {
    this.purchaseOrderLineList = purchaseOrderLineList;
  }

  /**
   * Add the given {@link PurchaseOrderLine} item to the {@code purchaseOrderLineList} collection.
   *
   * <p>
   * It sets {@code item.purchaseOrder = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPurchaseOrderLineListItem(PurchaseOrderLine item) {
    if (getPurchaseOrderLineList() == null) {
      setPurchaseOrderLineList(new ArrayList<>());
    }
    getPurchaseOrderLineList().add(item);
    item.setPurchaseOrder(this);
  }

  /**
   * Remove the given {@link PurchaseOrderLine} item from the {@code purchaseOrderLineList} collection.
   *
   * @param item the item to remove
   */
  public void removePurchaseOrderLineListItem(PurchaseOrderLine item) {
    if (getPurchaseOrderLineList() == null) {
      return;
    }
    getPurchaseOrderLineList().remove(item);
  }

  /**
   * Clear the {@code purchaseOrderLineList} collection.
   */
  public void clearPurchaseOrderLineList() {
    if (getPurchaseOrderLineList() != null) {
      getPurchaseOrderLineList().clear();
    }
  }

  public Integer getStatusSelect() {
    return statusSelect == null ? 0 : statusSelect;
  }

  public void setStatusSelect(Integer statusSelect) {
    this.statusSelect = statusSelect;
  }

  public String getExternalReference() {
    return externalReference;
  }

  public void setExternalReference(String externalReference) {
    this.externalReference = externalReference;
  }

  public String getInternalReference() {
    return internalReference;
  }

  public void setInternalReference(String internalReference) {
    this.internalReference = internalReference;
  }

  public Integer getReceiptState() {
    return receiptState == null ? 0 : receiptState;
  }

  public void setReceiptState(Integer receiptState) {
    this.receiptState = receiptState;
  }

  public BigDecimal getAdvanceTotal() {
    return advanceTotal == null ? BigDecimal.ZERO : advanceTotal;
  }

  public void setAdvanceTotal(BigDecimal advanceTotal) {
    this.advanceTotal = advanceTotal;
  }

  public TaxNumber getTaxNumber() {
    return taxNumber;
  }

  public void setTaxNumber(TaxNumber taxNumber) {
    this.taxNumber = taxNumber;
  }

  public FiscalPosition getFiscalPosition() {
    return fiscalPosition;
  }

  public void setFiscalPosition(FiscalPosition fiscalPosition) {
    this.fiscalPosition = fiscalPosition;
  }

  public List<PurchaseOrderLineTax> getPurchaseOrderLineTaxList() {
    return purchaseOrderLineTaxList;
  }

  public void setPurchaseOrderLineTaxList(List<PurchaseOrderLineTax> purchaseOrderLineTaxList) {
    this.purchaseOrderLineTaxList = purchaseOrderLineTaxList;
  }

  /**
   * Add the given {@link PurchaseOrderLineTax} item to the {@code purchaseOrderLineTaxList} collection.
   *
   * <p>
   * It sets {@code item.purchaseOrder = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPurchaseOrderLineTaxListItem(PurchaseOrderLineTax item) {
    if (getPurchaseOrderLineTaxList() == null) {
      setPurchaseOrderLineTaxList(new ArrayList<>());
    }
    getPurchaseOrderLineTaxList().add(item);
    item.setPurchaseOrder(this);
  }

  /**
   * Remove the given {@link PurchaseOrderLineTax} item from the {@code purchaseOrderLineTaxList} collection.
   *
   * @param item the item to remove
   */
  public void removePurchaseOrderLineTaxListItem(PurchaseOrderLineTax item) {
    if (getPurchaseOrderLineTaxList() == null) {
      return;
    }
    getPurchaseOrderLineTaxList().remove(item);
  }

  /**
   * Clear the {@code purchaseOrderLineTaxList} collection.
   */
  public void clearPurchaseOrderLineTaxList() {
    if (getPurchaseOrderLineTaxList() != null) {
      getPurchaseOrderLineTaxList().clear();
    }
  }

  public User getValidatedByUser() {
    return validatedByUser;
  }

  public void setValidatedByUser(User validatedByUser) {
    this.validatedByUser = validatedByUser;
  }

  public LocalDateTime getValidationDateTime() {
    return validationDateTime;
  }

  public void setValidationDateTime(LocalDateTime validationDateTime) {
    this.validationDateTime = validationDateTime;
  }

  public BigDecimal getExTaxTotal() {
    return exTaxTotal == null ? BigDecimal.ZERO : exTaxTotal;
  }

  public void setExTaxTotal(BigDecimal exTaxTotal) {
    this.exTaxTotal = exTaxTotal;
  }

  public BigDecimal getTaxTotal() {
    return taxTotal == null ? BigDecimal.ZERO : taxTotal;
  }

  public void setTaxTotal(BigDecimal taxTotal) {
    this.taxTotal = taxTotal;
  }

  public BigDecimal getInTaxTotal() {
    return inTaxTotal == null ? BigDecimal.ZERO : inTaxTotal;
  }

  public void setInTaxTotal(BigDecimal inTaxTotal) {
    this.inTaxTotal = inTaxTotal;
  }

  public BigDecimal getAmountToBeSpreadOverTheTimetable() {
    return amountToBeSpreadOverTheTimetable == null ? BigDecimal.ZERO : amountToBeSpreadOverTheTimetable;
  }

  public void setAmountToBeSpreadOverTheTimetable(BigDecimal amountToBeSpreadOverTheTimetable) {
    this.amountToBeSpreadOverTheTimetable = amountToBeSpreadOverTheTimetable;
  }

  public BigDecimal getCompanyExTaxTotal() {
    return companyExTaxTotal == null ? BigDecimal.ZERO : companyExTaxTotal;
  }

  public void setCompanyExTaxTotal(BigDecimal companyExTaxTotal) {
    this.companyExTaxTotal = companyExTaxTotal;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getInternalNote() {
    return internalNote;
  }

  public void setInternalNote(String internalNote) {
    this.internalNote = internalNote;
  }

  public String getSpecificNotes() {
    return specificNotes;
  }

  public void setSpecificNotes(String specificNotes) {
    this.specificNotes = specificNotes;
  }

  public Integer getVersionNumber() {
    return versionNumber == null ? 0 : versionNumber;
  }

  public void setVersionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
  }

  public Boolean getInAti() {
    return inAti == null ? Boolean.FALSE : inAti;
  }

  public void setInAti(Boolean inAti) {
    this.inAti = inAti;
  }

  public BankDetails getCompanyBankDetails() {
    return companyBankDetails;
  }

  public void setCompanyBankDetails(BankDetails companyBankDetails) {
    this.companyBankDetails = companyBankDetails;
  }

  public Boolean getDisplayPriceOnQuotationRequest() {
    return displayPriceOnQuotationRequest == null ? Boolean.FALSE : displayPriceOnQuotationRequest;
  }

  public void setDisplayPriceOnQuotationRequest(Boolean displayPriceOnQuotationRequest) {
    this.displayPriceOnQuotationRequest = displayPriceOnQuotationRequest;
  }

  public String getPriceRequest() {
    return priceRequest;
  }

  public void setPriceRequest(String priceRequest) {
    this.priceRequest = priceRequest;
  }

  public TradingName getTradingName() {
    return tradingName;
  }

  public void setTradingName(TradingName tradingName) {
    this.tradingName = tradingName;
  }

  public PrintingSettings getPrintingSettings() {
    return printingSettings;
  }

  public void setPrintingSettings(PrintingSettings printingSettings) {
    this.printingSettings = printingSettings;
  }

  public Boolean getGroupProductsOnPrintings() {
    return groupProductsOnPrintings == null ? Boolean.FALSE : groupProductsOnPrintings;
  }

  public void setGroupProductsOnPrintings(Boolean groupProductsOnPrintings) {
    this.groupProductsOnPrintings = groupProductsOnPrintings;
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
    return company != null ? company.getCurrency() : null;
  }

  public void setCompanyCurrency(Currency companyCurrency) {
    this.companyCurrency = companyCurrency;
  }

  public Integer getTypeSelect() {
    return typeSelect == null ? 0 : typeSelect;
  }

  public void setTypeSelect(Integer typeSelect) {
    this.typeSelect = typeSelect;
  }

  public Boolean getOrderBeingEdited() {
    return orderBeingEdited == null ? Boolean.FALSE : orderBeingEdited;
  }

  public void setOrderBeingEdited(Boolean orderBeingEdited) {
    this.orderBeingEdited = orderBeingEdited;
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
    if (!(obj instanceof PurchaseOrder)) return false;

    final PurchaseOrder other = (PurchaseOrder) obj;
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
      .add("purchaseOrderSeq", getPurchaseOrderSeq())
      .add("estimatedReceiptDate", getEstimatedReceiptDate())
      .add("orderDate", getOrderDate())
      .add("statusSelect", getStatusSelect())
      .add("externalReference", getExternalReference())
      .add("internalReference", getInternalReference())
      .add("receiptState", getReceiptState())
      .add("advanceTotal", getAdvanceTotal())
      .add("validationDateTime", getValidationDateTime())
      .add("exTaxTotal", getExTaxTotal())
      .omitNullValues()
      .toString();
  }
}
