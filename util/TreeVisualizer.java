package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.JScrollPane;

import tree.BinaryTree;
import tree.BinaryTreeNode;

public class TreeVisualizer {

	private static final int X_FACT=50;
	private static final int Y_FACT=50;
	private static final int MARGIN=50;
	private static final int RADIUS=10;
	private BinaryTree tree;
	private Map<BinaryTreeNode,Integer> xcoord;
	private Map<BinaryTreeNode,Integer> ycoord;
	
	private List<Ellipse2D> nodes;
	private List<Line2D> edges;
	private List<TreeLabel> labels;

	public TreeVisualizer(BinaryTree t) {
		super();
		tree=t;
		xcoord=new HashMap<>();
		ycoord=new HashMap<>();
		nodes=new LinkedList<>();
		edges=new LinkedList<>();
		labels=new LinkedList<>();
		assignYCoord(tree.root(),0);
		assignXCoord(tree.root());
		
		drawNodes();
		drawEdges();
		
	}

	private void drawEdges() {
		for(BinaryTreeNode n: xcoord.keySet()) {
			if(n.getParent()!=null) {
				BinaryTreeNode p=n.getParent();
				double nx=xcoord.get(n);
				double ny=ycoord.get(n);
				double px=xcoord.get(p);
				double py=ycoord.get(p);
				Line2D edge=new Line2D.Double(MARGIN+nx*X_FACT,MARGIN+ny*Y_FACT,MARGIN+px*X_FACT,MARGIN+py*Y_FACT);
				edges.add(edge);
			}
			
		}
		
	}

	private String GetStringNode(BinaryTreeNode btn){
		String c = btn.getInfo_C()+"";
		int frequenza = btn.getInfo_Freq();
		if(c.equals("£")){
			c = "";
			return ""+frequenza;
		}
		if (c.equals("\n")) {
			c = "INVIO";
		}
		if (c.equals(" ")) {
			c = "SPAZIO";
		}
		return c + "  :  "+ frequenza;
	}

	private void drawNodes() {
		for(BinaryTreeNode n: xcoord.keySet()) {
			int x=xcoord.get(n);
			int y=ycoord.get(n);
			Ellipse2D node=new Ellipse2D.Double(MARGIN+x*X_FACT-RADIUS, MARGIN+y*Y_FACT-RADIUS, 2*RADIUS, 2*RADIUS);
			nodes.add(node);
			labels.add(new TreeLabel(GetStringNode(n),node.getCenterX()-3,node.getCenterY()+3));
		}
	}

	private void assignXCoord(BinaryTreeNode n) {
		List<BinaryTreeNode> list=new LinkedList<>();
		assignXCoordRic(n,list);
		int i=0;
		for(ListIterator<BinaryTreeNode> it=list.listIterator();it.hasNext();) {
			BinaryTreeNode n1=it.next();
			xcoord.put(n1,i++);
		}
	}	
		
	private void assignXCoordRic(BinaryTreeNode n, List<BinaryTreeNode> list) {
			
		if(n!=null) {
			assignXCoordRic(n.getLeft(),list);
			list.add(n);
			assignXCoordRic(n.getRight(),list);	
		}
	}

	private void assignYCoord(BinaryTreeNode n, int level) {
		if(n!=null) {
			ycoord.put(n, level);
			assignYCoord(n.getLeft(), level+1);
			assignYCoord(n.getRight(), level+1);
		}
	}
	
	public List<Ellipse2D> getNodes() {
		return nodes;
	}

	public List<Line2D> getEdges() {
		return edges;
	}
	
	public List<TreeLabel> getLabels() {
		return labels;
	}

	public void show(String title) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TreeFrame frame=new TreeFrame(TreeVisualizer.this);
                frame.setTitle(title);       
         }
		});
	}
}


class TreeFrame extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TreePanel panel;

    /** Creates new form TreeMapFrame */
    public TreeFrame(TreeVisualizer tv) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        panel=new TreePanel();
        panel.setNodes(tv.getNodes());
        panel.setEdges(tv.getEdges());
        panel.setLabels(tv.getLabels());
        JScrollPane scroll=new JScrollPane(panel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(scroll);
        this.pack();
        this.setVisible(true);
    }

}


class TreePanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Ellipse2D> nodes;
	private List<Line2D> edges;
	private List<TreeLabel> labels;
	
	private Color color;

    /** Creates new form TreeMapPanel */
    public TreePanel() {
    	this.setPreferredSize(new Dimension(800,600));
        this.color=Color.PINK;
    }
    
    public void setNodes(List<Ellipse2D> nodes) {
		this.nodes = nodes;
	}


	public void setEdges(List<Line2D> edges) {
		this.edges = edges;
	}
	
	public void setLabels(List<TreeLabel> labels) {
		this.labels = labels;
	}


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        
        if(edges!=null){
            for(Line2D ln: edges){
                g2.setColor(Color.black);
                g2.draw(ln);
            }
        }
        int wMax=0;
        int hMax=0;
        
        if(nodes!=null){
            for(Ellipse2D el: nodes){
                g2.setColor(color);
                g2.fill(el);
                g2.setColor(Color.black);
                g2.draw(el);
                if(el.getCenterX()+el.getWidth()>wMax)
                	wMax=(int)(el.getCenterX()+el.getWidth());
                if(el.getCenterY()+el.getHeight()>hMax)
                	hMax=(int)(el.getCenterY()+el.getHeight());
            }
        }
        
        if(labels!=null){
            for(TreeLabel lab: labels){
                g2.setColor(Color.black);
                g2.drawString(lab.getLabel(),(int)lab.getX(),(int)lab.getY());
            }
        }
        
        this.setPreferredSize(new Dimension(wMax,hMax));

    }
}


class TreeLabel {
	
	private String label;
	private double x;
	private double y;
	
	public TreeLabel(String label, double x, double y) {
		super();
		this.label = label;
		this.x = x;
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	

}
