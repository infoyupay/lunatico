package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxtools.FxSnapshotTreeItem;
import javafx.scene.control.TreeItem;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * Flow to fill a tree view model with the live system snapshot view.
 * It won't store anything in the database, only will show information
 * to the user.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxListLiveSnapshotFlow {
    /**
     * It triggers the process of <strong>R</strong>etrieving data
     * from the database, use it to prepare the snapshot tree view model
     * and send to the GUI layer through a Consumer object, usually
     * this will look like:
     * {@snippet :
     *    import javafx.scene.control.TreeTableView;
     *
     *    public class MyController{
     *
     *        TreeTableView<FxSnapshotTreeItem> treeTable;
     *
     *        void onUserAction(){
     *            new FxListLiveSnapshotFlow().go(treeTable::setRoot);
     *        }
     *    }
     *}
     *
     * @param onSuccess the consumer object.
     */
    public void go(@NotNull Consumer<TreeItem<FxSnapshotTreeItem>> onSuccess) {
        var rootMV = new FxSnapshotTreeItem();
        var rootV = new TreeItem<>(rootMV);

        try (var em = DataSource.em()) {
            //Collect all active items.
            var activeItems = DAOFactory.item()
                    .listActive(em)
                    .map(FxSnapshotTreeItem::new)
                    .map(TreeItem::new)
                    .peek(x -> x.setExpanded(false))
                    .toList();

            //Collect all balances overviews.
            var balances = DAOFactory
                    .balanceOverview()
                    .listAll(em)
                    .toList();
            //Add all balance overviews as children of their items.
            activeItems.parallelStream()
                    .forEach(it -> balances.stream()
                            .filter(ov -> ov.getItemId() == it.getValue().getItemId())
                            .map(FxSnapshotTreeItem::new)
                            .map(TreeItem::new)
                            .forEach(it.getChildren()::add));

            //Sumarize the totals.
            rootV.getChildren().setAll(activeItems);
            activeItems.parallelStream()
                    .map(x -> x.getValue().getGlobalCost())
                    .reduce(BigDecimal::add)
                    .ifPresent(rootMV::setGlobalCost);
        } catch (RuntimeException e) {
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al generar el estado vivo del inventairo.")
                    .accept(e);
        }
        //GUI tricks.
        rootV.setExpanded(true);
        //Send to the consumer.
        onSuccess.accept(rootV);
    }
}
