package com.axelor.apps.base.db;

import com.axelor.apps.purchase.db.PurchaseConfig;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.message.db.EmailAccount;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "BASE_COMPANY",
  indexes = {
    @Index(
      columnList = "address"
    ),
    @Index(
      columnList = "partner"
    ),
    @Index(
      columnList = "parent"
    ),
    @Index(
      columnList = "logo"
    ),
    @Index(
      columnList = "light_logo"
    ),
    @Index(
      columnList = "dark_logo"
    ),
    @Index(
      columnList = "currency"
    ),
    @Index(
      columnList = "default_bank_details"
    ),
    @Index(
      columnList = "printing_settings"
    ),
    @Index(
      columnList = "weekly_planning"
    ),
    @Index(
      columnList = "public_holiday_events_planning"
    ),
    @Index(
      columnList = "localization"
    )
  }
)
@Track(
  fields = {
    @TrackField(
      name = "name",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "code",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "address",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "partner",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "parent",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "notes",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "logo",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "lightLogo",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "darkLogo",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "currency",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "customerPaymentDelay",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "supplierPaymentDelay",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "defaultBankDetails",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "printingSettings",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "defaultPartnerTypeSelect",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "defaultPartnerCategorySelect",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "weeklyPlanning",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "publicHolidayEventsPlanning",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "localization",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "width",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "height",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "timezone",
      on = TrackEvent.UPDATE
    )
  }
)
public class Company extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_COMPANY_SEQ"
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "Name"
  )
  @NotNull
  @Column(
    unique = true
  )
  private String name;

  @EqualsInclude
  @Widget(
    title = "Code"
  )
  @NotNull
  @Column(
    unique = true
  )
  private String code;

  @Widget(
    title = "Address",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Address address;

  @Widget(
    title = "Partner"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner partner;

  @Widget(
    title = "Parent company",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company parent;

  @Widget(
    title = "Company departments"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<CompanyDepartment> companyDepartmentList;

  @Widget(
    title = "Notes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String notes;

  @Widget(
    title = "Logo",
    help = "The default logo, used in BIRT printings."
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile logo;

  @Widget(
    title = "Light logo",
    help = "If non empty, this alternative logo will be displayed for users using a light mode-based theme."
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile lightLogo;

  @Widget(
    title = "Dark logo",
    help = "If non empty, this alternative logo will be displayed for users using a dark mode-based theme."
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile darkLogo;

  @Widget(
    title = "Currency",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Currency currency;

  @Widget(
    title = "Customers payment delay (Average in days)"
  )
  private BigDecimal customerPaymentDelay = BigDecimal.ZERO;

  @Widget(
    title = "Suppliers payment delay (Average in days)"
  )
  private BigDecimal supplierPaymentDelay = BigDecimal.ZERO;

  @Widget(
    title = "Default Bank Account"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private BankDetails defaultBankDetails;

  @Widget(
    title = "Bank accounts"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<BankDetails> bankDetailsList;

  @Widget(
    title = "Printing Settings",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PrintingSettings printingSettings;

  @Widget(
    title = "Partner Type",
    massUpdate = true,
    selection = "company.partner.type.select"
  )
  private Integer defaultPartnerTypeSelect = 1;

  @Widget(
    title = "Partner default category",
    massUpdate = true,
    selection = "company.partner.category.select"
  )
  private Integer defaultPartnerCategorySelect = 0;

  @Widget(
    title = "Trading names"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<TradingName> tradingNameList;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<TradingNamePrintingSettings> tradingNamePrintingSettingsList;

  @Widget(
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private WeeklyPlanning weeklyPlanning;

  @Widget(
    title = "Public Holiday Planning",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private EventsPlanning publicHolidayEventsPlanning;

  @Widget(
    title = "Localization",
    massUpdate = true
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Localization localization;

  @Widget(
    title = "Width"
  )
  @Min(0)
  private Integer width = 0;

  @Widget(
    title = "Height",
    help = "Maximum height should be 60 px."
  )
  @Min(0)
  @Max(60)
  private Integer height = 0;

  @Widget(
    title = "Attached partners"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private List<Partner> partnerList;

  @Widget(
    title = "Time Zone",
    selection = "company.timezone.select"
  )
  private String timezone;

  @Widget(
    title = "Email accounts"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<EmailAccount> emailAccountList;

  @Widget(
    title = "Purchase config"
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "company",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PurchaseConfig purchaseConfig;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public Company() {
  }

  public Company(String name, String code) {
    this.name = name;
    this.code = code;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Partner getPartner() {
    return partner;
  }

  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  public Company getParent() {
    return parent;
  }

  public void setParent(Company parent) {
    this.parent = parent;
  }

  public List<CompanyDepartment> getCompanyDepartmentList() {
    return companyDepartmentList;
  }

  public void setCompanyDepartmentList(List<CompanyDepartment> companyDepartmentList) {
    this.companyDepartmentList = companyDepartmentList;
  }

  /**
   * Add the given {@link CompanyDepartment} item to the {@code companyDepartmentList} collection.
   *
   * <p>
   * It sets {@code item.company = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addCompanyDepartmentListItem(CompanyDepartment item) {
    if (getCompanyDepartmentList() == null) {
      setCompanyDepartmentList(new ArrayList<>());
    }
    getCompanyDepartmentList().add(item);
    item.setCompany(this);
  }

  /**
   * Remove the given {@link CompanyDepartment} item from the {@code companyDepartmentList} collection.
   *
   * @param item the item to remove
   */
  public void removeCompanyDepartmentListItem(CompanyDepartment item) {
    if (getCompanyDepartmentList() == null) {
      return;
    }
    getCompanyDepartmentList().remove(item);
  }

  /**
   * Clear the {@code companyDepartmentList} collection.
   */
  public void clearCompanyDepartmentList() {
    if (getCompanyDepartmentList() != null) {
      getCompanyDepartmentList().clear();
    }
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * The default logo, used in BIRT printings.
   *
   * @return the property value
   */
  public MetaFile getLogo() {
    return logo;
  }

  public void setLogo(MetaFile logo) {
    this.logo = logo;
  }

  /**
   * If non empty, this alternative logo will be displayed for users using a light mode-based theme.
   *
   * @return the property value
   */
  public MetaFile getLightLogo() {
    return lightLogo;
  }

  public void setLightLogo(MetaFile lightLogo) {
    this.lightLogo = lightLogo;
  }

  /**
   * If non empty, this alternative logo will be displayed for users using a dark mode-based theme.
   *
   * @return the property value
   */
  public MetaFile getDarkLogo() {
    return darkLogo;
  }

  public void setDarkLogo(MetaFile darkLogo) {
    this.darkLogo = darkLogo;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public BigDecimal getCustomerPaymentDelay() {
    return customerPaymentDelay == null ? BigDecimal.ZERO : customerPaymentDelay;
  }

  public void setCustomerPaymentDelay(BigDecimal customerPaymentDelay) {
    this.customerPaymentDelay = customerPaymentDelay;
  }

  public BigDecimal getSupplierPaymentDelay() {
    return supplierPaymentDelay == null ? BigDecimal.ZERO : supplierPaymentDelay;
  }

  public void setSupplierPaymentDelay(BigDecimal supplierPaymentDelay) {
    this.supplierPaymentDelay = supplierPaymentDelay;
  }

  public BankDetails getDefaultBankDetails() {
    return defaultBankDetails;
  }

  public void setDefaultBankDetails(BankDetails defaultBankDetails) {
    this.defaultBankDetails = defaultBankDetails;
  }

  public List<BankDetails> getBankDetailsList() {
    return bankDetailsList;
  }

  public void setBankDetailsList(List<BankDetails> bankDetailsList) {
    this.bankDetailsList = bankDetailsList;
  }

  /**
   * Add the given {@link BankDetails} item to the {@code bankDetailsList} collection.
   *
   * <p>
   * It sets {@code item.company = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addBankDetailsListItem(BankDetails item) {
    if (getBankDetailsList() == null) {
      setBankDetailsList(new ArrayList<>());
    }
    getBankDetailsList().add(item);
    item.setCompany(this);
  }

  /**
   * Remove the given {@link BankDetails} item from the {@code bankDetailsList} collection.
   *
   * @param item the item to remove
   */
  public void removeBankDetailsListItem(BankDetails item) {
    if (getBankDetailsList() == null) {
      return;
    }
    getBankDetailsList().remove(item);
  }

  /**
   * Clear the {@code bankDetailsList} collection.
   */
  public void clearBankDetailsList() {
    if (getBankDetailsList() != null) {
      getBankDetailsList().clear();
    }
  }

  public PrintingSettings getPrintingSettings() {
    return printingSettings;
  }

  public void setPrintingSettings(PrintingSettings printingSettings) {
    this.printingSettings = printingSettings;
  }

  public Integer getDefaultPartnerTypeSelect() {
    return defaultPartnerTypeSelect == null ? 0 : defaultPartnerTypeSelect;
  }

  public void setDefaultPartnerTypeSelect(Integer defaultPartnerTypeSelect) {
    this.defaultPartnerTypeSelect = defaultPartnerTypeSelect;
  }

  public Integer getDefaultPartnerCategorySelect() {
    return defaultPartnerCategorySelect == null ? 0 : defaultPartnerCategorySelect;
  }

  public void setDefaultPartnerCategorySelect(Integer defaultPartnerCategorySelect) {
    this.defaultPartnerCategorySelect = defaultPartnerCategorySelect;
  }

  public List<TradingName> getTradingNameList() {
    return tradingNameList;
  }

  public void setTradingNameList(List<TradingName> tradingNameList) {
    this.tradingNameList = tradingNameList;
  }

  /**
   * Add the given {@link TradingName} item to the {@code tradingNameList} collection.
   *
   * <p>
   * It sets {@code item.company = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addTradingNameListItem(TradingName item) {
    if (getTradingNameList() == null) {
      setTradingNameList(new ArrayList<>());
    }
    getTradingNameList().add(item);
    item.setCompany(this);
  }

  /**
   * Remove the given {@link TradingName} item from the {@code tradingNameList} collection.
   *
   * @param item the item to remove
   */
  public void removeTradingNameListItem(TradingName item) {
    if (getTradingNameList() == null) {
      return;
    }
    getTradingNameList().remove(item);
  }

  /**
   * Clear the {@code tradingNameList} collection.
   */
  public void clearTradingNameList() {
    if (getTradingNameList() != null) {
      getTradingNameList().clear();
    }
  }

  public List<TradingNamePrintingSettings> getTradingNamePrintingSettingsList() {
    return tradingNamePrintingSettingsList;
  }

  public void setTradingNamePrintingSettingsList(List<TradingNamePrintingSettings> tradingNamePrintingSettingsList) {
    this.tradingNamePrintingSettingsList = tradingNamePrintingSettingsList;
  }

  /**
   * Add the given {@link TradingNamePrintingSettings} item to the {@code tradingNamePrintingSettingsList} collection.
   *
   * <p>
   * It sets {@code item.company = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addTradingNamePrintingSettingsListItem(TradingNamePrintingSettings item) {
    if (getTradingNamePrintingSettingsList() == null) {
      setTradingNamePrintingSettingsList(new ArrayList<>());
    }
    getTradingNamePrintingSettingsList().add(item);
    item.setCompany(this);
  }

  /**
   * Remove the given {@link TradingNamePrintingSettings} item from the {@code tradingNamePrintingSettingsList} collection.
   *
   * @param item the item to remove
   */
  public void removeTradingNamePrintingSettingsListItem(TradingNamePrintingSettings item) {
    if (getTradingNamePrintingSettingsList() == null) {
      return;
    }
    getTradingNamePrintingSettingsList().remove(item);
  }

  /**
   * Clear the {@code tradingNamePrintingSettingsList} collection.
   */
  public void clearTradingNamePrintingSettingsList() {
    if (getTradingNamePrintingSettingsList() != null) {
      getTradingNamePrintingSettingsList().clear();
    }
  }

  public WeeklyPlanning getWeeklyPlanning() {
    return weeklyPlanning;
  }

  public void setWeeklyPlanning(WeeklyPlanning weeklyPlanning) {
    this.weeklyPlanning = weeklyPlanning;
  }

  public EventsPlanning getPublicHolidayEventsPlanning() {
    return publicHolidayEventsPlanning;
  }

  public void setPublicHolidayEventsPlanning(EventsPlanning publicHolidayEventsPlanning) {
    this.publicHolidayEventsPlanning = publicHolidayEventsPlanning;
  }

  public Localization getLocalization() {
    return localization;
  }

  public void setLocalization(Localization localization) {
    this.localization = localization;
  }

  public Integer getWidth() {
    return width == null ? 0 : width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  /**
   * Maximum height should be 60 px.
   *
   * @return the property value
   */
  public Integer getHeight() {
    return height == null ? 0 : height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public List<Partner> getPartnerList() {
    return partnerList;
  }

  public void setPartnerList(List<Partner> partnerList) {
    this.partnerList = partnerList;
  }

  /**
   * Add the given {@link Partner} item to the {@code partnerList} collection.
   *
   * @param item the item to add
   */
  public void addPartnerListItem(Partner item) {
    if (getPartnerList() == null) {
      setPartnerList(new ArrayList<>());
    }
    getPartnerList().add(item);
  }

  /**
   * Remove the given {@link Partner} item from the {@code partnerList} collection.
   *
   * @param item the item to remove
   */
  public void removePartnerListItem(Partner item) {
    if (getPartnerList() == null) {
      return;
    }
    getPartnerList().remove(item);
  }

  /**
   * Clear the {@code partnerList} collection.
   */
  public void clearPartnerList() {
    if (getPartnerList() != null) {
      getPartnerList().clear();
    }
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public List<EmailAccount> getEmailAccountList() {
    return emailAccountList;
  }

  public void setEmailAccountList(List<EmailAccount> emailAccountList) {
    this.emailAccountList = emailAccountList;
  }

  /**
   * Add the given {@link EmailAccount} item to the {@code emailAccountList} collection.
   *
   * <p>
   * It sets {@code item.company = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addEmailAccountListItem(EmailAccount item) {
    if (getEmailAccountList() == null) {
      setEmailAccountList(new ArrayList<>());
    }
    getEmailAccountList().add(item);
    item.setCompany(this);
  }

  /**
   * Remove the given {@link EmailAccount} item from the {@code emailAccountList} collection.
   *
   * @param item the item to remove
   */
  public void removeEmailAccountListItem(EmailAccount item) {
    if (getEmailAccountList() == null) {
      return;
    }
    getEmailAccountList().remove(item);
  }

  /**
   * Clear the {@code emailAccountList} collection.
   */
  public void clearEmailAccountList() {
    if (getEmailAccountList() != null) {
      getEmailAccountList().clear();
    }
  }

  public PurchaseConfig getPurchaseConfig() {
    return purchaseConfig;
  }

  public void setPurchaseConfig(PurchaseConfig purchaseConfig) {
    if (getPurchaseConfig() != null) {
      getPurchaseConfig().setCompany(null);
    }
    if (purchaseConfig != null) {
      purchaseConfig.setCompany(this);
    }
    this.purchaseConfig = purchaseConfig;
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
    if (!(obj instanceof Company)) return false;

    final Company other = (Company) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getName(), other.getName())
      && Objects.equals(getCode(), other.getCode())
      && (getName() != null
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
      .add("code", getCode())
      .add("customerPaymentDelay", getCustomerPaymentDelay())
      .add("supplierPaymentDelay", getSupplierPaymentDelay())
      .add("defaultPartnerTypeSelect", getDefaultPartnerTypeSelect())
      .add("defaultPartnerCategorySelect", getDefaultPartnerCategorySelect())
      .add("width", getWidth())
      .add("height", getHeight())
      .add("timezone", getTimezone())
      .omitNullValues()
      .toString();
  }
}
