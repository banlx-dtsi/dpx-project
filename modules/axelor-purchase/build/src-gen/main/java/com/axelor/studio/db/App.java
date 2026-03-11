package com.axelor.studio.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "STUDIO_APP",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "image"
    )
  }
)
public class App extends AuditableModel {

  @Id
  @EntitySequence(
    name = "STUDIO_APP_SEQ"
  )
  private Long id;

  @Widget(
    translatable = true,
    title = "Name"
  )
  @NotNull
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
    title = "Modules contains in the app"
  )
  private String modules;

  @Widget(
    title = "Depends on"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<App> dependsOnSet;

  @Widget(
    title = "Image"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile image;

  @Widget(
    title = "Init data loaded"
  )
  private Boolean initDataLoaded = Boolean.FALSE;

  @Widget(
    title = "Demo data loaded"
  )
  private Boolean demoDataLoaded = Boolean.FALSE;

  @Widget(
    title = "Roles imported"
  )
  private Boolean isRolesImported = Boolean.FALSE;

  @Widget(
    title = "Installed"
  )
  private Boolean active = Boolean.FALSE;

  @Widget(
    title = "Sequence"
  )
  private Integer sequence = 0;

  @Widget(
    title = "Install order"
  )
  private Integer installOrder = 0;

  @Widget(
    title = "Language",
    selection = "select.language"
  )
  private String languageSelect;

  @Widget(
    title = "Type",
    selection = "studio.app.type.select"
  )
  private String typeSelect = "others";

  @Widget(
    title = "Version"
  )
  private String appVersion;

  @Widget(
    title = "Custom"
  )
  private Boolean isCustom = Boolean.FALSE;

  @Widget(
    title = "Experimental"
  )
  private Boolean isExperimental = Boolean.FALSE;

  @Widget(
    title = "App Studio"
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "app",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private AppStudio appStudio;

  @Widget(
    title = "App BPM"
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "app",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private AppBpm appBpm;

  @Widget(
    title = "Add the application to the App Management"
  )
  private Boolean isInAppView = Boolean.FALSE;

  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "app",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private AppBase appBase;

  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "app",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private AppPurchase appPurchase;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public App() {
  }

  public App(String name, String code) {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getModules() {
    return modules;
  }

  public void setModules(String modules) {
    this.modules = modules;
  }

  public Set<App> getDependsOnSet() {
    return dependsOnSet;
  }

  public void setDependsOnSet(Set<App> dependsOnSet) {
    this.dependsOnSet = dependsOnSet;
  }

  /**
   * Add the given {@link App} item to the {@code dependsOnSet} collection.
   *
   * @param item the item to add
   */
  public void addDependsOnSetItem(App item) {
    if (getDependsOnSet() == null) {
      setDependsOnSet(new HashSet<>());
    }
    getDependsOnSet().add(item);
  }

  /**
   * Remove the given {@link App} item from the {@code dependsOnSet} collection.
   *
   * @param item the item to remove
   */
  public void removeDependsOnSetItem(App item) {
    if (getDependsOnSet() == null) {
      return;
    }
    getDependsOnSet().remove(item);
  }

  /**
   * Clear the {@code dependsOnSet} collection.
   */
  public void clearDependsOnSet() {
    if (getDependsOnSet() != null) {
      getDependsOnSet().clear();
    }
  }

  public MetaFile getImage() {
    return image;
  }

  public void setImage(MetaFile image) {
    this.image = image;
  }

  public Boolean getInitDataLoaded() {
    return initDataLoaded == null ? Boolean.FALSE : initDataLoaded;
  }

  public void setInitDataLoaded(Boolean initDataLoaded) {
    this.initDataLoaded = initDataLoaded;
  }

  public Boolean getDemoDataLoaded() {
    return demoDataLoaded == null ? Boolean.FALSE : demoDataLoaded;
  }

  public void setDemoDataLoaded(Boolean demoDataLoaded) {
    this.demoDataLoaded = demoDataLoaded;
  }

  public Boolean getIsRolesImported() {
    return isRolesImported == null ? Boolean.FALSE : isRolesImported;
  }

  public void setIsRolesImported(Boolean isRolesImported) {
    this.isRolesImported = isRolesImported;
  }

  public Boolean getActive() {
    return active == null ? Boolean.FALSE : active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getSequence() {
    return sequence == null ? 0 : sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Integer getInstallOrder() {
    return installOrder == null ? 0 : installOrder;
  }

  public void setInstallOrder(Integer installOrder) {
    this.installOrder = installOrder;
  }

  public String getLanguageSelect() {
    return languageSelect;
  }

  public void setLanguageSelect(String languageSelect) {
    this.languageSelect = languageSelect;
  }

  public String getTypeSelect() {
    return typeSelect;
  }

  public void setTypeSelect(String typeSelect) {
    this.typeSelect = typeSelect;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public Boolean getIsCustom() {
    return isCustom == null ? Boolean.FALSE : isCustom;
  }

  public void setIsCustom(Boolean isCustom) {
    this.isCustom = isCustom;
  }

  public Boolean getIsExperimental() {
    return isExperimental == null ? Boolean.FALSE : isExperimental;
  }

  public void setIsExperimental(Boolean isExperimental) {
    this.isExperimental = isExperimental;
  }

  public AppStudio getAppStudio() {
    return appStudio;
  }

  public void setAppStudio(AppStudio appStudio) {
    if (getAppStudio() != null) {
      getAppStudio().setApp(null);
    }
    if (appStudio != null) {
      appStudio.setApp(this);
    }
    this.appStudio = appStudio;
  }

  public AppBpm getAppBpm() {
    return appBpm;
  }

  public void setAppBpm(AppBpm appBpm) {
    if (getAppBpm() != null) {
      getAppBpm().setApp(null);
    }
    if (appBpm != null) {
      appBpm.setApp(this);
    }
    this.appBpm = appBpm;
  }

  public Boolean getIsInAppView() {
    return isInAppView == null ? Boolean.FALSE : isInAppView;
  }

  public void setIsInAppView(Boolean isInAppView) {
    this.isInAppView = isInAppView;
  }

  public AppBase getAppBase() {
    return appBase;
  }

  public void setAppBase(AppBase appBase) {
    if (getAppBase() != null) {
      getAppBase().setApp(null);
    }
    if (appBase != null) {
      appBase.setApp(this);
    }
    this.appBase = appBase;
  }

  public AppPurchase getAppPurchase() {
    return appPurchase;
  }

  public void setAppPurchase(AppPurchase appPurchase) {
    if (getAppPurchase() != null) {
      getAppPurchase().setApp(null);
    }
    if (appPurchase != null) {
      appPurchase.setApp(this);
    }
    this.appPurchase = appPurchase;
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
    if (!(obj instanceof App)) return false;

    final App other = (App) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getCode(), other.getCode())
      && (getCode() != null);
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
      .add("modules", getModules())
      .add("initDataLoaded", getInitDataLoaded())
      .add("demoDataLoaded", getDemoDataLoaded())
      .add("isRolesImported", getIsRolesImported())
      .add("active", getActive())
      .add("sequence", getSequence())
      .add("installOrder", getInstallOrder())
      .add("languageSelect", getLanguageSelect())
      .omitNullValues()
      .toString();
  }
}
