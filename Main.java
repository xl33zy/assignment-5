public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

//        tree.put(3, "Fuse tea");
//        tree.put(5, "Coca Cola");
//        tree.put(7, "Fanta");
//        tree.put(2, "Sprite");
//        tree.put(4, "Maxi tea");
//        tree.put(6, "Lipton");
//        tree.put(8, "Gorilla");

        tree.put(1, "Fuse tea");
        tree.put(2, "Coca Cola");
        tree.put(3, "Fanta");
        tree.put(4, "Sprite");
        tree.put(5, "Maxi tea");
        tree.put(6, "Lipton");
        tree.put(7, "Gorilla");

//        System.out.println("Value at key 2: " + tree.get(2));
//        System.out.println("Value at key 4: " + tree.get(4));

//        tree.delete(3);
//        tree.delete(8);

        System.out.println("In-order traversal:");
        for (BinarySearchTree.Node node : tree) {
            System.out.println("Key is: " + node.key + ", and value is: " + node.value);
        }

        System.out.println("Height of the tree " + tree.height());
        System.out.println("Size of the tree: " + tree.size());
    }
}
