package tree;

public class Nodo {
        //Represent the node of binary tree    
       
            FrequencyChar data;    
            Nodo left;    
            Nodo right;   
            public Nodo(){
                this.data = null;    
                this.left = null;    
                this.right = null;  
            } 
            public Nodo(FrequencyChar data){    
                //Assign data to the new node, set left and right children to null    
                this.data = data;    
                this.left = null;    
                this.right = null;    
                }  
                
                

            }   
