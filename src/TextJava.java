public class TextJava {

    public static void main(String[] arg0){
        String s = "adjjs";
        s.toCharArray();s.getBytes();
//        hasCycle();
    }

    //这是数据结构的基本题，判断链表是否有环，直接快慢指针搞定，也可以用set去存储，碰撞了就有环
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
            return false;
        ListNode slow=head;
        ListNode quick=head.next;
        while(slow!=quick)
        {
            if(quick==null||quick.next==null)
            {
                return false;
            }
            else
            {
                slow=slow.next;
                quick=quick.next.next;
            }
        }
        return true;
    }

//----------------------------------------------------------------------------------------------------------------------
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
