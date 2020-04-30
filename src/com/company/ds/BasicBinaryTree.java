package com.company.ds;

public class BasicBinaryTree<T extends Comparable<T>> {
    private Node root;
    private int size;

    public BasicBinaryTree() {
        this.root = null;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        Node node = new Node(item);

        if(root == null) {
            this.root = node;
            System.out.println("Set root: " + node.getItem());
            this.size++;
        } else {
            insert(this.root, node);
        }
    }

    private void insert(Node parent, Node child) {
        if(child.getItem().compareTo(parent.getItem()) < 0) {
            if(parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            } else {
                insert(parent.getLeft(), child);
            }
        } else if(child.getItem().compareTo(parent.getItem()) > 0) {
            if(parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            } else {
                insert(parent.getRight(), child);
            }
        }
    }

    public boolean contains(T item) {
        Node node = getNode(item);

        if(node == null) {
            return false;
        } else {
            return true;
        }
    }

    private Node getNode(T item) {
        Node node = this.root;
        while(node != null) {
            if(node.getItem().compareTo(item) == 0) {
                return node;
            } else if(item.compareTo(node.getItem()) < 0) {
                node = node.getLeft();
            } else if(item.compareTo(node.getItem()) > 0) {
                node = node.getRight();
            }
        }

        return null;
    }

    public boolean delete(T item) {
        boolean deleted = false;

        if(this.root == null) {
            return false;
        }

        Node currentNode = getNode(item);

        if(currentNode != null) {
            if(currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                deleted = true;
            } else if(currentNode.getLeft() == null && currentNode.getRight() != null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            } else if(currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            } else {
                Node child = currentNode.getLeft();
                while(child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                child.getParent().setRight(null);

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }

        if(deleted) {
            this.size--;
        }

        return deleted;
    }

    private void unlink(Node currentNode, Node newNode) {
        if(currentNode == this.root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        } else if(currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        } else {
            currentNode.getParent().setLeft(newNode);
        }
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private T item;

        public Node(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }
}
