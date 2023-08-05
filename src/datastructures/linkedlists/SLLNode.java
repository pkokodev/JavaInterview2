package datastructures.linkedlists;

public class SLLNode {
    int data;
    SLLNode next;

    SLLNode(){

    }
    public SLLNode(int data) {
        this.data = data;
    }

    void insertAtEnd(int data, SLLNode head){
        if(head == null){
            return;
        }
        if(head.next == null){
            head.next = new SLLNode(data);
            return;
        }else {
            insertAtEnd(data, head.next);
        }
    }


    // head act as temp pointer starting from head
    void insertAtEndItr(int data, SLLNode head){
        while (head.next != null){
            head = head.next;
        }
        head.next = new SLLNode(data);
    }

    void insertAll(SLLNode head, String nodesStr){
        //SLLNode temp = head;//for making loop
        for (String node: nodesStr.split(" ")) {
            if(head.data == 0){
                head.data = Integer.parseInt(node);
                continue;
            }
           head.next = new SLLNode(Integer.parseInt(node));
           head = head.next;
        }
        //head.next = temp;//for making loop
    }

    String printList(SLLNode head){
        if(head.next == null){
            return " " + head.data;
        }
        return " " + head.data + printList(head.next);
    }
}
