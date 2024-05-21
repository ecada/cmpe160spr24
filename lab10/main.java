public class main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.print();
        System.out.println(tree.minValue());
        System.out.println(tree.maxValue());
        tree.delete(20);
        tree.delete(30);
        tree.print();
        System.out.println(tree.search(50));
        System.out.println(tree.search(30));
        tree.delete(50);
        tree.delete(70);
        tree.print();
        System.out.println(tree.search(50));
        System.out.println(tree.search(40));
        tree.insert(35);
        tree.insert(45);
        tree.insert(95);
        System.out.println(tree.minValue());
        System.out.println(tree.maxValue());
    }
}
