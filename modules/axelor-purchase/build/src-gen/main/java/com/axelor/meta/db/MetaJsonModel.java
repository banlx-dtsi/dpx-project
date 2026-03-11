package com.axelor.meta.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.Role;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.ValueEnumType;
import com.axelor.studio.db.StudioApp;
import com.axelor.studio.db.StudioMenu;
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
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "META_JSON_MODEL",
  indexes = {
    @Index(
      columnList = "title"
    ),
    @Index(
      columnList = "menu"
    ),
    @Index(
      columnList = "menu_parent"
    ),
    @Index(
      columnList = "action"
    ),
    @Index(
      columnList = "grid_view"
    ),
    @Index(
      columnList = "form_view"
    ),
    @Index(
      columnList = "studio_app"
    ),
    @Index(
      columnList = "studio_menu"
    )
  }
)
public class MetaJsonModel extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_JSON_MODEL_SEQ"
  )
  private Long id;

  @EqualsInclude
  @NotNull
  @Column(
    unique = true
  )
  private String name;

  @NameColumn
  @NotNull
  private String title;

  private String onNew;

  private String onSave;

  private String nameField;

  private String formWidth;

  private String orderBy;

  private String groupBy;

  @Widget(
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaMenu menu;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaMenu menuParent;

  private String menuIcon;

  private String menuBackground;

  private Integer menuOrder = 0;

  private String menuTitle;

  @Widget(
    title = "Messaging panel"
  )
  @Basic
  @Type(ValueEnumType.class)
  private PanelMailDisplay panelMailDisplay;

  @Widget(
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaAction action;

  @Widget(
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaView gridView;

  @Widget(
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaView formView;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "jsonModel",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OrderBy("sequence,id")
  private List<MetaJsonField> fields;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Role> roles;

  @Widget(
    title = "App"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private StudioApp studioApp;

  @Widget(
    title = "Show process tracking"
  )
  private Boolean showProcessTracking = Boolean.FALSE;

  @Widget(
    title = "Generate menu"
  )
  private Boolean isGenerateMenu = Boolean.FALSE;

  @Widget(
    title = "StudioMenu.menu"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private StudioMenu studioMenu;

  public MetaJsonModel() {
  }

  public MetaJsonModel(String name) {
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOnNew() {
    return onNew;
  }

  public void setOnNew(String onNew) {
    this.onNew = onNew;
  }

  public String getOnSave() {
    return onSave;
  }

  public void setOnSave(String onSave) {
    this.onSave = onSave;
  }

  public String getNameField() {
    return nameField;
  }

  public void setNameField(String nameField) {
    this.nameField = nameField;
  }

  public String getFormWidth() {
    return formWidth;
  }

  public void setFormWidth(String formWidth) {
    this.formWidth = formWidth;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(String groupBy) {
    this.groupBy = groupBy;
  }

  public MetaMenu getMenu() {
    return menu;
  }

  public void setMenu(MetaMenu menu) {
    this.menu = menu;
  }

  public MetaMenu getMenuParent() {
    return menuParent;
  }

  public void setMenuParent(MetaMenu menuParent) {
    this.menuParent = menuParent;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public String getMenuBackground() {
    return menuBackground;
  }

  public void setMenuBackground(String menuBackground) {
    this.menuBackground = menuBackground;
  }

  public Integer getMenuOrder() {
    return menuOrder == null ? 0 : menuOrder;
  }

  public void setMenuOrder(Integer menuOrder) {
    this.menuOrder = menuOrder;
  }

  public String getMenuTitle() {
    return menuTitle;
  }

  public void setMenuTitle(String menuTitle) {
    this.menuTitle = menuTitle;
  }

  public PanelMailDisplay getPanelMailDisplay() {
    return panelMailDisplay;
  }

  public void setPanelMailDisplay(PanelMailDisplay panelMailDisplay) {
    this.panelMailDisplay = panelMailDisplay;
  }

  public MetaAction getAction() {
    return action;
  }

  public void setAction(MetaAction action) {
    this.action = action;
  }

  public MetaView getGridView() {
    return gridView;
  }

  public void setGridView(MetaView gridView) {
    this.gridView = gridView;
  }

  public MetaView getFormView() {
    return formView;
  }

  public void setFormView(MetaView formView) {
    this.formView = formView;
  }

  public List<MetaJsonField> getFields() {
    return fields;
  }

  public void setFields(List<MetaJsonField> fields) {
    this.fields = fields;
  }

  /**
   * Add the given {@link MetaJsonField} item to the {@code fields} collection.
   *
   * <p>
   * It sets {@code item.jsonModel = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addField(MetaJsonField item) {
    if (getFields() == null) {
      setFields(new ArrayList<>());
    }
    getFields().add(item);
    item.setJsonModel(this);
  }

  /**
   * Remove the given {@link MetaJsonField} item from the {@code fields} collection.
   *
   * @param item the item to remove
   */
  public void removeField(MetaJsonField item) {
    if (getFields() == null) {
      return;
    }
    getFields().remove(item);
  }

  /**
   * Clear the {@code fields} collection.
   */
  public void clearFields() {
    if (getFields() != null) {
      getFields().clear();
    }
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * Add the given {@link Role} item to the {@code roles} collection.
   *
   * @param item the item to add
   */
  public void addRole(Role item) {
    if (getRoles() == null) {
      setRoles(new HashSet<>());
    }
    getRoles().add(item);
  }

  /**
   * Remove the given {@link Role} item from the {@code roles} collection.
   *
   * @param item the item to remove
   */
  public void removeRole(Role item) {
    if (getRoles() == null) {
      return;
    }
    getRoles().remove(item);
  }

  /**
   * Clear the {@code roles} collection.
   */
  public void clearRoles() {
    if (getRoles() != null) {
      getRoles().clear();
    }
  }

  public StudioApp getStudioApp() {
    return studioApp;
  }

  public void setStudioApp(StudioApp studioApp) {
    this.studioApp = studioApp;
  }

  public Boolean getShowProcessTracking() {
    return showProcessTracking == null ? Boolean.FALSE : showProcessTracking;
  }

  public void setShowProcessTracking(Boolean showProcessTracking) {
    this.showProcessTracking = showProcessTracking;
  }

  public Boolean getIsGenerateMenu() {
    return isGenerateMenu == null ? Boolean.FALSE : isGenerateMenu;
  }

  public void setIsGenerateMenu(Boolean isGenerateMenu) {
    this.isGenerateMenu = isGenerateMenu;
  }

  public StudioMenu getStudioMenu() {
    return studioMenu;
  }

  public void setStudioMenu(StudioMenu studioMenu) {
    this.studioMenu = studioMenu;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaJsonModel)) return false;

    final MetaJsonModel other = (MetaJsonModel) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getName(), other.getName())
      && (getName() != null);
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
      .add("title", getTitle())
      .add("onNew", getOnNew())
      .add("onSave", getOnSave())
      .add("nameField", getNameField())
      .add("formWidth", getFormWidth())
      .add("orderBy", getOrderBy())
      .add("groupBy", getGroupBy())
      .add("menuIcon", getMenuIcon())
      .add("menuBackground", getMenuBackground())
      .omitNullValues()
      .toString();
  }
}
