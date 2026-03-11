# Plan to Simplify Purchase Workflow for Vietnam Market

This plan outlines the steps to remove the "Purchase Request" feature and simplify the "Purchase Order" workflow by bypassing the quotation (RFQ) stage in the `axelor-purchase` module.

## Proposed Changes

### 1. Remove "Purchase Request" Feature
To completely remove/hide the Purchase Request feature:
- **Hide Menu**: Modify the menu configuration to hide the "Internal purchase requests" menu.
- **Deactivate App**: Disable the `purchase-request` app definition which is used as a condition for some UI elements.

#### [MODIFY] [Menu.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/Menu.xml)
- Comment out or remove the menuitem `sc-root-purchase-request`.

#### [MODIFY] [purchase-request.yml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/apps/purchase-request.yml)
- Change the app code or comment out the content to disable the app feature `purchase-request`.

---

### 2. Simplify Purchase Order Flow (Bypass Quotation)
Currently, the flow is: **Draft -> Requested (RFQ) -> Validated (PO)**.
The simplified flow will be: **Draft -> Validated (PO)**.

#### [MODIFY] [Menu.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/Menu.xml)
- **Hide Quotations Menu**: Comment out or remove the menuitem `sc-root-purchase-quotations`.
- **Update Purchase Orders Menu**: Modify the `domain` of `sc.root.purchase.orders` to include **Draft** status so that new orders are visible immediately.
  - From: `<domain>self.statusSelect IN (3)</domain>`
  - To: `<domain>self.statusSelect IN (1, 3)</domain>`

#### [MODIFY] [PurchaseOrder.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/PurchaseOrder.xml)
- **Simplify Buttons**:
  - In `purchase-order-form`, modify the `requestedBtn` (which currently sets status to 2) to act as a "Validate" button that sets status directly to 3.
  - Assign `action-group-purchase-order-on-validate-actions` to this button.
  - Hide the original `validateBtn` (which only shows for status 2).
- **Update Dynamic Title**:
  - In the field `purchaseOrderSeq` viewer, change the label for status 1 from "Quotation" to "Draft" or "Purchase Order".

#### [MODIFY] [Selects.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/Selects.xml)
- (Optional) Remove the "Requested" option from `purchase.purchase.order.status.select` to clean up the UI dropdowns.

## Verification Plan

### Manual Verification
1.  **Check Menus**: Login to the application and verify that "Internal purchase requests" and "Purchase quotations" menus are gone.
2.  **Create PO**:
    *   Go to "Purchase orders" menu.
    *   Click "New". Verify the title is "Draft" or "Purchase Order" (not "Quotation").
    *   Fill in details and click the newly renamed "Validate" button.
    *   Verify the status changes directly to "Validated".
3.  **Check Grid**: Verify that the newly created Draft order is visible in the "Purchase orders" list.
