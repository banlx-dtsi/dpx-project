package com.axelor.message.db;

import com.axelor.apps.base.db.Localization;
import com.axelor.apps.base.db.PrintingTemplate;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.meta.db.MetaJsonModel;
import com.axelor.meta.db.MetaModel;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "MESSAGE_TEMPLATE",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "meta_model"
    ),
    @Index(
      columnList = "meta_json_model"
    ),
    @Index(
      columnList = "mail_account"
    )
  }
)
public class Template extends AuditableModel {

  @Id
  @EntitySequence(
    name = "MESSAGE_TEMPLATE_SEQ"
  )
  private Long id;

  @Widget(
    title = "Name"
  )
  @NameColumn
  @NotNull
  private String name;

  @Widget(
    title = "Content",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String content;

  @Widget(
    title = "Subject",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String subject;

  @Widget(
    title = "Reply to",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String replyToRecipients;

  @Widget(
    title = "To",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String toRecipients;

  @Widget(
    title = "Cc",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String ccRecipients;

  @Widget(
    title = "Bcc",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String bccRecipients;

  @Widget(
    title = "Target receptor"
  )
  private String target;

  @Widget(
    title = "Media Type",
    selection = "message.media.type.select"
  )
  @NotNull
  private Integer mediaTypeSelect = 2;

  @Widget(
    title = "Address Block",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String addressBlock;

  @Widget(
    title = "Mobile phone"
  )
  private String toMobilePhone;

  @Widget(
    title = "Model"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaModel metaModel;

  @Widget(
    title = "Default"
  )
  private Boolean isDefault = Boolean.FALSE;

  @Widget(
    title = "System"
  )
  private Boolean isSystem = Boolean.FALSE;

  @Widget(
    title = "Json"
  )
  private Boolean isJson = Boolean.FALSE;

  @Widget(
    title = "Model"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaJsonModel metaJsonModel;

  @Widget(
    title = "Mail Account"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private EmailAccount mailAccount;

  @Widget(
    title = "Context"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "template",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<TemplateContext> templateContextList;

  @Widget(
    title = "Signature"
  )
  private String signature;

  @Widget(
    title = "Add Signature"
  )
  private Boolean addSignature = Boolean.FALSE;

  @Widget(
    title = "Template engine",
    selection = "template.engine.select"
  )
  private Integer templateEngineSelect = 1;

  @Widget(
    title = "Language",
    selection = "select.language"
  )
  private String language;

  @Widget(
    title = "Print template"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<PrintingTemplate> printTemplateSet;

  @Widget(
    title = "Localization"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Localization> localizationSet;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public Template() {
  }

  public Template(String name) {
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getReplyToRecipients() {
    return replyToRecipients;
  }

  public void setReplyToRecipients(String replyToRecipients) {
    this.replyToRecipients = replyToRecipients;
  }

  public String getToRecipients() {
    return toRecipients;
  }

  public void setToRecipients(String toRecipients) {
    this.toRecipients = toRecipients;
  }

  public String getCcRecipients() {
    return ccRecipients;
  }

  public void setCcRecipients(String ccRecipients) {
    this.ccRecipients = ccRecipients;
  }

  public String getBccRecipients() {
    return bccRecipients;
  }

  public void setBccRecipients(String bccRecipients) {
    this.bccRecipients = bccRecipients;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public Integer getMediaTypeSelect() {
    return mediaTypeSelect == null ? 0 : mediaTypeSelect;
  }

  public void setMediaTypeSelect(Integer mediaTypeSelect) {
    this.mediaTypeSelect = mediaTypeSelect;
  }

  public String getAddressBlock() {
    return addressBlock;
  }

  public void setAddressBlock(String addressBlock) {
    this.addressBlock = addressBlock;
  }

  public String getToMobilePhone() {
    return toMobilePhone;
  }

  public void setToMobilePhone(String toMobilePhone) {
    this.toMobilePhone = toMobilePhone;
  }

  public MetaModel getMetaModel() {
    return metaModel;
  }

  public void setMetaModel(MetaModel metaModel) {
    this.metaModel = metaModel;
  }

  public Boolean getIsDefault() {
    return isDefault == null ? Boolean.FALSE : isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public Boolean getIsSystem() {
    return isSystem == null ? Boolean.FALSE : isSystem;
  }

  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  public Boolean getIsJson() {
    return isJson == null ? Boolean.FALSE : isJson;
  }

  public void setIsJson(Boolean isJson) {
    this.isJson = isJson;
  }

  public MetaJsonModel getMetaJsonModel() {
    return metaJsonModel;
  }

  public void setMetaJsonModel(MetaJsonModel metaJsonModel) {
    this.metaJsonModel = metaJsonModel;
  }

  public EmailAccount getMailAccount() {
    return mailAccount;
  }

  public void setMailAccount(EmailAccount mailAccount) {
    this.mailAccount = mailAccount;
  }

  public List<TemplateContext> getTemplateContextList() {
    return templateContextList;
  }

  public void setTemplateContextList(List<TemplateContext> templateContextList) {
    this.templateContextList = templateContextList;
  }

  /**
   * Add the given {@link TemplateContext} item to the {@code templateContextList} collection.
   *
   * <p>
   * It sets {@code item.template = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addTemplateContextListItem(TemplateContext item) {
    if (getTemplateContextList() == null) {
      setTemplateContextList(new ArrayList<>());
    }
    getTemplateContextList().add(item);
    item.setTemplate(this);
  }

  /**
   * Remove the given {@link TemplateContext} item from the {@code templateContextList} collection.
   *
   * @param item the item to remove
   */
  public void removeTemplateContextListItem(TemplateContext item) {
    if (getTemplateContextList() == null) {
      return;
    }
    getTemplateContextList().remove(item);
  }

  /**
   * Clear the {@code templateContextList} collection.
   */
  public void clearTemplateContextList() {
    if (getTemplateContextList() != null) {
      getTemplateContextList().clear();
    }
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public Boolean getAddSignature() {
    return addSignature == null ? Boolean.FALSE : addSignature;
  }

  public void setAddSignature(Boolean addSignature) {
    this.addSignature = addSignature;
  }

  public Integer getTemplateEngineSelect() {
    return templateEngineSelect == null ? 0 : templateEngineSelect;
  }

  public void setTemplateEngineSelect(Integer templateEngineSelect) {
    this.templateEngineSelect = templateEngineSelect;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Set<PrintingTemplate> getPrintTemplateSet() {
    return printTemplateSet;
  }

  public void setPrintTemplateSet(Set<PrintingTemplate> printTemplateSet) {
    this.printTemplateSet = printTemplateSet;
  }

  /**
   * Add the given {@link PrintingTemplate} item to the {@code printTemplateSet} collection.
   *
   * @param item the item to add
   */
  public void addPrintTemplateSetItem(PrintingTemplate item) {
    if (getPrintTemplateSet() == null) {
      setPrintTemplateSet(new HashSet<>());
    }
    getPrintTemplateSet().add(item);
  }

  /**
   * Remove the given {@link PrintingTemplate} item from the {@code printTemplateSet} collection.
   *
   * @param item the item to remove
   */
  public void removePrintTemplateSetItem(PrintingTemplate item) {
    if (getPrintTemplateSet() == null) {
      return;
    }
    getPrintTemplateSet().remove(item);
  }

  /**
   * Clear the {@code printTemplateSet} collection.
   */
  public void clearPrintTemplateSet() {
    if (getPrintTemplateSet() != null) {
      getPrintTemplateSet().clear();
    }
  }

  public Set<Localization> getLocalizationSet() {
    return localizationSet;
  }

  public void setLocalizationSet(Set<Localization> localizationSet) {
    this.localizationSet = localizationSet;
  }

  /**
   * Add the given {@link Localization} item to the {@code localizationSet} collection.
   *
   * @param item the item to add
   */
  public void addLocalizationSetItem(Localization item) {
    if (getLocalizationSet() == null) {
      setLocalizationSet(new HashSet<>());
    }
    getLocalizationSet().add(item);
  }

  /**
   * Remove the given {@link Localization} item from the {@code localizationSet} collection.
   *
   * @param item the item to remove
   */
  public void removeLocalizationSetItem(Localization item) {
    if (getLocalizationSet() == null) {
      return;
    }
    getLocalizationSet().remove(item);
  }

  /**
   * Clear the {@code localizationSet} collection.
   */
  public void clearLocalizationSet() {
    if (getLocalizationSet() != null) {
      getLocalizationSet().clear();
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
    if (!(obj instanceof Template)) return false;

    final Template other = (Template) obj;
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
      .add("target", getTarget())
      .add("mediaTypeSelect", getMediaTypeSelect())
      .add("toMobilePhone", getToMobilePhone())
      .add("isDefault", getIsDefault())
      .add("isSystem", getIsSystem())
      .add("isJson", getIsJson())
      .add("signature", getSignature())
      .add("addSignature", getAddSignature())
      .add("templateEngineSelect", getTemplateEngineSelect())
      .omitNullValues()
      .toString();
  }
}
