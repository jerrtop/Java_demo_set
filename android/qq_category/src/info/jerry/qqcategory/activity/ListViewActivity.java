package info.jerry.qqcategory.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class ListViewActivity extends Activity {
	ExpandableListView expandableListView;
	ListViewAdapter treeViewAdapter;
	private String[] group = new String[]{"列表1","列表2","列表3"};
	private String[][] child = new String[][]{{ "" }, { "" }, { "", "" }};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        treeViewAdapter = new ListViewAdapter(this, ListViewAdapter.paddingLeft >> 1);
        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        List<ListViewAdapter.TreeNode> treeNodes = treeViewAdapter.getTreeNodes();
        for(int i = 0; i < group.length ; i ++){
        	ListViewAdapter.TreeNode node = new ListViewAdapter.TreeNode();
        	node.parent = group[i];
        	for (int ii = 0; ii < child[i].length; ii++)
			{
				node.childs.add(child[i][ii]);
			}
			treeNodes.add(node);
        }
        treeViewAdapter.UpdateTreeNode(treeNodes);
        expandableListView.setAdapter(treeViewAdapter);
    }
}