package tree;

public class BinaryTree {

	private BinaryTreeNode root;
	
	/* Crea un albero binario vuoto */
	public BinaryTree(BinaryTreeNode root){
		this.root=root;
	}

	/* Restituisce true se l'albero binario è vuoto */
	public boolean isEmpty() {
		return this.root==null;
	}

	/* Restituisce la radice dell'albero */
	public BinaryTreeNode root() {
		return this.root;
	}
	
	/* Restituisce il nodo il cui dato associato è info, o null se tale nodod non esiste */
	public BinaryTreeNode search(Object info) {
		return searchRic(this.root,info);
	}

	private BinaryTreeNode searchRic(BinaryTreeNode n, Object info){
		if(n!=null){
			if(n.getInfo().equals(info))
				return n;

			BinaryTreeNode left=searchRic(n.getLeft(),info);
			if(left!=null)
				return left;

			BinaryTreeNode right=searchRic(n.getRight(),info);
			if(right!=null)
				return right;
			return null;
		}
		return null;
	}

	/* Aggiunge la radice all'albero. Questo metodo dovrebbe essere invocato su un albero vuoto; se viene invocato su un albero non vuoto l'albero esistente viene cancellato */
	public BinaryTreeNode addRoot(FrequencyChar info) {
		root= new BinaryTreeNode(info);
		return root;
	}

	/* Aggiunge un nodo con dato associato info come figlio sinistro del nodo n */
	public BinaryTreeNode addLeftChild(BinaryTreeNode n, FrequencyChar info) {
		BinaryTreeNode c= new BinaryTreeNode(info);
		n.setLeft(c);
		c.setParent(n);
		return c;
	}
	
	/* Aggiunge un nodo con dato associato info come figlio destro del nodo n */
	public BinaryTreeNode addRightChild(BinaryTreeNode n, FrequencyChar info) {
		BinaryTreeNode c= new BinaryTreeNode(info);
		n.setRight(c);
		c.setParent(n);
		return c;
	}

	/* Aggiunge l'albero l come sottoalbero sinistro del nodo n */
	public void addLeftSubtree(BinaryTreeNode n, BinaryTree l) {
		BinaryTreeNode lr=l.root();
		n.setLeft(lr);
		lr.setParent(n);
	}

	/* Aggiunge l'albero r come sottoalbero destro del nodo n */
	public void addRightSubtree(BinaryTreeNode n, BinaryTree r) {
		BinaryTreeNode rr=r.root();
		n.setRight(rr);
		rr.setParent(n);

	}
	
	/* Restituisce una descrizione testuale dell'albero */
	public String toString(){
		return print2(root,"");
	}

	private String print2(BinaryTreeNode n, String c) {
		String s="";
		if(n!=null){
			s+=c+n+"\n";
			s+=print2(n.getLeft(),c+" ");
			s+=print2(n.getRight(),c+" ");
		}
		return s;
	}
	

}
