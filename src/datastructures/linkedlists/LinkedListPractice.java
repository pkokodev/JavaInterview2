package datastructures.linkedlists;

public class LinkedListPractice {
    public static void main(String[] args) {
        SLLNode head = new SLLNode();
        head.insertAll(head, "100 200 300 400 500 700");
        //SLLNode node = reverseRec(head);
        //System.out.println(printList(node));

        //System.out.println(middleItr(head, head));

        //System.out.println(isCyclePresent(head));
        //System.out.println(cyclePointAndBreakLoop(head));

        System.out.println(copyLLWithRandom(head));

    }

    //Clone a Linked List with Next and Random Pointer
    private static SLLNode copyLLWithRandom(SLLNode head){
        return new SLLNode();
    }

    private static SLLNode cyclePointAndBreakLoop(SLLNode head) {
        SLLNode meet = isCyclePresent(head);
        //Cycle point
        /*while (meet == head){
            head = head.next;
            meet = meet.next;
        }*/
        //to break loop stop one node before Cycle point
        while (meet.next == head.next){
            head = head.next;
            meet = meet.next;
        }
        meet.next = null;
        return meet;
    }

    static int cycleLenghth(SLLNode head){
        SLLNode slow = head;
        int lenght = 0;
        while (head.next != null && head.next.next != null){

            slow = slow.next;
            head = head.next.next;
            if(slow.data == head.data){
                break;
            }
        }

        while (head.data != slow.data){
            lenght++;
            head = head.next;
        }
        return lenght;
    }

    //Floyd’s Cycle-Finding Algorithm
    static SLLNode isCyclePresent(SLLNode head){
        SLLNode slow = head;
        while (head != null && head.next != null){

            slow = slow.next;
            head = head.next.next;
            if(slow == head){
                System.out.println(true);
                return slow;
            }
        }
        System.out.println(false);
        return head;
    }
    static SLLNode kthLastNode(SLLNode head , int k){
        SLLNode slow = head;
        int i = 0 - k;
        while (head.next != null){
            i++;
            if(i >= 0){
                slow = slow.next;
            }

            head = head.next;
        }
        return slow;
    }

    static int middleItr(SLLNode head, SLLNode slow){
        while (head != null && head.next != null){
            slow = slow.next;
            head = head.next.next;
        }
       return slow.data;
    }
    static int middleRec(SLLNode head, SLLNode slow){
        if(head == null || head.next == null){
            return slow.data;
        }
       return middleRec(head.next.next, slow.next);
    }
    static SLLNode reverseRec(SLLNode head){

        if(head == null || head.next == null){
            return head;
        }

        SLLNode temp = reverseRec(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    /*
    1. create new reversed list and check T = O(2n) S = O(n)
    2. go to middle of the list using slow and fast pointer,
    and we can not go backward from middle so reverse that list from middle and then do the comparison.
    T = O(n+n/2) S = (1)
    */
    static boolean isPalindrome(SLLNode head){
        SLLNode slow = head;
        SLLNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        SLLNode revHead = reverse(slow.next);
        while (revHead != null){
            if(revHead.data != head.data){
               return false;
            }
            revHead =revHead.next;
            head = head.next;
        }
        return true;
    }
    static SLLNode reverse(SLLNode head){
        SLLNode prev = null;
        SLLNode curr = head;
        SLLNode temp = head;
        while (curr != null){
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    static SLLNode rotateCWByKNodes(SLLNode head, int k){
        SLLNode slow = head;
        SLLNode fast = head;
        /*int j = 0 - k;
        while (fast.next != null){
            if(j >= 0){
               slow = slow.next;
            }
            fast = fast.next;
            j++;
        }*/

        while (fast.next != null){
            if(k == 0){
                slow = slow.next;
            }else {
                k--;
            }
            fast = fast.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
    private static int search(SLLNode head, int data) {
        /*if(head == null){
            return -1;
        }
        if(head.data == data){
            return data;
        }
        return search(head.next, data);*/

        while (head.next != null){
            if(head.data == data){
                return data;
            }
            head = head.next;
        }

        return -1;
    }

    private static int size(SLLNode head) {
        /*if(head == null){
            return 0;
        }
        return 1 + size(head.next);*/
        int count = 1;
        while (head.next != null){
            head = head.next;
            count++;
        }

        return count;
    }

    static void deleteAtPos(SLLNode head, int pos){
        if(pos == 1){
            head.next = head.next.next;
            return;
        }
        deleteAtPos(head.next, pos - 1);
    }

    static void deleteAtPosItr(SLLNode head, int pos){
        int i = 1;
        /*while (i < pos){
            head = head.next;
            i++;
        }
        head.next = head.next.next;*/

        // Copy next node data to current node and delete next node
        while (i <= pos){
            head = head.next;
            i++;
        }
        head.data = head.next.data;
        head.next = head.next.next;
    }

    static String printList(SLLNode head){
        if(head.next == null){
            return "" + head.data;
        }
        return head.data + "--> " + printList(head.next);
    }
}
