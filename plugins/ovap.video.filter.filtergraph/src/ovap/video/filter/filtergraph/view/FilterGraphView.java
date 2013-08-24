/**
 * 
 *//*
package ovap.video.filter.filtergraph.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.CGraphNode;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphContainer;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.core.widgets.internal.ContainerFigure;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;

import ovap.filter.setup.model.FilterConnection;
import ovap.filter.setup.model.FilterDescription;
import ovap.filter.setup.model.FilterInstance;
import ovap.filter.setup.model.FiltersSetup;

*//**
 * @author Creative
 *
 *//*
public class FilterGraphView extends ViewPart {
	private Composite cmpstMain;
	private Composite cmpstGraph;
	private Composite cmpstTools;
	private Graph graph;
	private Button btnAdd;
	private Button btnRemove;
	
	*//**
	 * 
	 *//*
	public FilterGraphView() {
		// TODO Auto-generated constructor stub
	}

	 (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 
	@Override
	public void createPartControl(Composite parent) {
		{
			cmpstMain = new Composite(parent, SWT.NONE);
			cmpstMain.setLayout(new GridLayout(2, false));
			{
				cmpstGraph = new Composite(cmpstMain, SWT.NONE);
				cmpstGraph.setLayout(new FillLayout(SWT.HORIZONTAL));
				{
					GridData gd_cmpstGraph = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
					gd_cmpstGraph.heightHint = 456;
					gd_cmpstGraph.widthHint = 483;
					cmpstGraph.setLayoutData(gd_cmpstGraph);
					
					graph = new Graph(cmpstGraph, SWT.BORDER);
					graph.setLayout(new FillLayout(SWT.HORIZONTAL));
					graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(), true);
				}
			}
			{
				cmpstTools = new Composite(cmpstMain, SWT.NONE);
				{
					GridData gd_cmpstTools = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
					gd_cmpstTools.heightHint = 74;
					gd_cmpstTools.widthHint = 90;
					cmpstTools.setLayoutData(gd_cmpstTools);
				}
				{
					btnAdd = new Button(cmpstTools, SWT.NONE);
					btnAdd.setBounds(10, 10, 68, 23);
					btnAdd.setText("Add");
				}
				{
					btnRemove = new Button(cmpstTools, SWT.NONE);
					btnRemove.setBounds(10, 39, 68, 23);
					btnRemove.setText("Remove");
				}
			}
		}
		
		
		GraphContainer container= new GraphContainer(graph, SWT.NONE, "Container");
		
		CGraphNode cGraphNode = new CGraphNode(graph, SWT.NONE, new ContainerFigure());
		
		// TODO Auto-generated method stub
		
		FiltersSetup filtersSetup = new FiltersSetup();
		FilterInstance filterInstance1 = new FilterInstance();
		filterInstance1.setName("filter1");
		FilterDescription description1 = new FilterDescription();
		description1.setID("filter.id1");
		filterInstance1.setDescription(description1);
		
		FilterInstance filterInstance2 = new FilterInstance();
		filterInstance2.setName("filter2");
		FilterDescription description2 = new FilterDescription();
		description2.setID("filter.id2");
		filterInstance2.setDescription(description2);
		
		filtersSetup.getFilterInstances().add(filterInstance1);
		filtersSetup.getFilterInstances().add(filterInstance2);
		
		
		FilterConnection connection = new FilterConnection();
		connection.setSrcFilterInstance(filterInstance1);
		connection.setDstFilterInstance(filterInstance2);
		filtersSetup.getConnections().add(connection );
		
		loadSetup(filtersSetup);
	}

	 (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public boolean loadSetup(FiltersSetup filtersSetup){
		
		// create filters
		for(FilterInstance filterInstance:filtersSetup.getFilterInstances()){
			GraphNode node = new GraphNode(graph, SWT.NULL, filterInstance.getName(), filterInstance);
		}
		
		
		// create connections
		for(FilterConnection connection:filtersSetup.getConnections()){
			GraphNode srcNode = getNode(connection.getSrcFilterInstance());
			GraphNode dstNode = getNode(connection.getDstFilterInstance());
			GraphConnection graphConnection = new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, srcNode, dstNode);
		}
		
		graph.layout();
		
		return true;
	}
	
	public GraphNode getNode(FilterInstance filterInstance){
		List<GraphNode> nodes = graph.getNodes();
		for(GraphNode node:nodes){
			if(node.getData()==filterInstance)
				return node;
		}
		return null;
	}
}
*/