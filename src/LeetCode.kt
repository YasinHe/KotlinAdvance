import java.util.*

fun main(args: Array<String>) {
    var result = 0
//    reverse(1534236469)

//    longestCommonPrefix(arrayOf("babb","caa"))//"flower","flow","flight"   "a","a"   "ca","a"   "c","acc","ccc"  "babb","caa"

//    result = isValid("()") //()  ()[]{}   (]   ([)]  {[]}   ()[]{}

//    removeElement(intArrayOf(0,1,2,2,3,0,4,2),2)//3,2,2,3

//    strStr("a","a")

//    result = searchInsert(intArrayOf(1,3,4,5,6,10), 1)

//    result = maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4))

//    result = lengthOfLastWord("a ")

//    result = addBinary("1010","1011")//10101

//    result = mySqrt(4)

//    result = climbStairs2(6)

    var listNode1 = ListNode(2)
    var listNode2 = ListNode(4)
    var listNode3 = ListNode(3)
    listNode1.next = listNode2
    listNode2.next = listNode3
    var listNode4 = ListNode(5)
    var listNode5 = ListNode(5)
    var listNode6 = ListNode(6)
    var listNode7 = ListNode(7)
    var listNode8 = ListNode(7)
    listNode4.next = listNode5
    listNode5.next = listNode6
    listNode6.next = listNode7
    listNode7.next = listNode8
//    addTwoNumbers(listNode1,listNode4)//708

//    deleteDuplicates(listNode4)

    var treeNode = TreeNode(1)
    var treeNode2 = TreeNode(2)
    treeNode.left = treeNode2
    treeNode.right = null
    var treeNode3 = TreeNode(1)
    var treeNode4 = TreeNode(2)
    treeNode3.left = treeNode4

//    val boolean = isSameTree(treeNode,treeNode3)

//    merge(intArrayOf(0,0,0,0,0),
//            0,
//            intArrayOf(1,2,3,4,5),
//            5)//[1,2,2,3,5,6]

//    val boolean = isSymmetric(treeNode)

//    result = maxDepth(treeNode)

//    levelOrderBottom(treeNode)

//    val data = sortedArrayToBST(intArrayOf(0,1,2,3,4,5))

//    isBalanced(treeNode)

//    minDepth(treeNode3)

    hasPathSum(treeNode,3)

      println(result)
}

fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    var result = false

    return result
}

fun minDepth(root: TreeNode?): Int {
    //求树的最小深度，就是找到一个没有孩子的叶子结点，这个结点深度最小
    if(root==null) return 0
    if(root.left==null&&root.right==null) return 1
    var leftDeep = if(root.left==null) 0 else minDepth(root.left)+1
    var rightDeep = if(root.right==null) 0 else minDepth(root.right)+1
    return if(leftDeep==0) rightDeep else if(rightDeep==0) leftDeep else if (leftDeep>rightDeep) rightDeep else leftDeep
}

fun isBalanced(root: TreeNode?): Boolean {
    //判断一颗二叉树是否平衡，也就是所有的左右子树的高低差不能大于1
    if(root==null) return true
    var dValue = maxDepth(root.left) - maxDepth(root.right)
    if ( (dValue < -1) || ( dValue > 1)){
        return false
    } else {
        return (isBalanced(root.left) && isBalanced(root.right))
    }
}

fun sortedArrayToBST(nums: IntArray): TreeNode? {
    //这题因为是排序好的数组，而且要求平衡，那就是从中间取值，两边作为左右子树 左右子树再取中间为 根节点的左右节点
    if(nums.isEmpty()){
        return null
    }
    if(nums.size==1){
        return TreeNode(nums[0])
    }
    var treeNode:TreeNode = TreeNode(nums[nums.size/2])
    treeNode.left = sortedArrayToBST(nums.copyOfRange(0,nums.size/2))
    treeNode.right = sortedArrayToBST(nums.copyOfRange(if(nums.size==2&&nums.size/2==1) 2 else nums.size/2+1,nums.size))
    return treeNode
}

fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    var result: MutableList<List<Int>> = mutableListOf()
    if(root==null){
        return result
    }
    //接下来对左右结点下手
    var list:MutableList<TreeNode> = mutableListOf()
    list.add(root)
    var temp: MutableList<Int> = mutableListOf()
    while (!list.isEmpty()){
        var size = list.size
        for(index in 0 until size){
            val treeNode = list.get(index)
            temp.add(treeNode.`val`)
            if(treeNode.left!=null){
                list.add(treeNode.left!!)
            }
            if(treeNode.right!=null){
                list.add(treeNode.right!!)
            }
        }
        for(index in 0 until size){
            list.removeAt(0)
        }
        result.add(temp)
        temp = mutableListOf()
    }
    return result.reversed().toList()
}

tailrec fun maxDepth(root: TreeNode?): Int {
    if(root==null) return 0
    //求二叉树的最大深度也就是最远的叶子节点+1
    var leftDeep = maxDepth(root.left)+1
    var rightDeep = maxDepth(root.right)+1
    return if (leftDeep>rightDeep) leftDeep else rightDeep
}

fun isSymmetric(root: TreeNode?): Boolean {
    //思路是把二叉树分为左子树和右子树，左子树分别做先根遍历（但是一个根左右  一个根右左）
    if(root==null) return true
    return traverse2(root.left,true)==traverse2(root.right,false)
}

tailrec fun traverse2(tree:TreeNode?,left: Boolean):String{
    if(tree==null) return "null"
    if(tree.`val`==null) return "null"
    var stringBuilder = StringBuilder()
    stringBuilder.append(if(tree!=null)tree.`val`.toString() else "null")
    if(left) {
        stringBuilder.append(traverse2(tree.left,left))
        stringBuilder.append(traverse2(tree.right,left))
    }else{
        stringBuilder.append(traverse2(tree.right,left))
        stringBuilder.append(traverse2(tree.left,left))
    }
    return stringBuilder.toString()
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    if(nums2.isEmpty()){
        return
    }
    var indexAll = m+n-1
    var index1 = m
    var index2 = n
    if(indexAll==0&&m==0){
        if(nums1[0]<nums2[0]){
            nums1[0] = nums2[0]
        }
    }
    while(indexAll>=0){
        if(index1<=0){//说明m已经遍历结束了
            nums1[indexAll] = nums2[index2-1]
            index2--
        }else if(index2<=0){//说明n已经遍历结束
            nums1[indexAll] = nums1[index1-1]
            index1--
        }else{
            if(nums1[index1-1]<=nums2[index2-1]){
                nums1[indexAll] = nums2[index2-1]
                index2--
            } else{
                nums1[indexAll] = nums1[index1-1]
                index1--
            }
        }
        indexAll--
    }
    if(indexAll==0){
        if(nums1[0]>nums2[0]){
            nums1[0] = nums2[0]
        }
    }
    System.out.print("ok")
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if(p==null&&q==null) return true
    if(p==null||q==null) return false
    return traverse(p)==traverse(q)
}

tailrec fun traverse(tree:TreeNode?):String{
    if(tree==null) return "null"
    if(tree.`val`==null) return "null"
    var stringBuilder = StringBuilder()
    stringBuilder.append(if(tree!=null)tree.`val`.toString() else "null")
    stringBuilder.append(traverse(tree.left))
    stringBuilder.append(traverse(tree.right))
    return stringBuilder.toString()
}

fun deleteDuplicates(head: ListNode?): ListNode? {
    if(head==null) return head
    var temp = head.`val`
    var lopper = head.next
    var current = head
    while (lopper!=null){
        if(temp== lopper!!.`val`){
            lopper = lopper.next
            if(lopper==null){
                current!!.next = null
            }else {
                current!!.next = lopper
            }
        }else{
            temp = lopper!!.`val`
            lopper = lopper.next
            if (current != null) {
                current = current.next
            }
        }
    }
    return head
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if(l1==null) return l2
    if(l2==null) return l1
    var temp = l1
    var s1 = ""
    while(temp!=null){
       s1 = s1+"${temp.`val`}"
        temp = temp.next
    }
    var s2 = ""
    temp = l2
    while(temp!=null){
        s2 = s2+"${temp.`val`}"
        temp = temp.next
    }
    //用例这里要判断最大值
    var result = ""
    try {
        result = "${ if((s1.reversed().toInt() + s2.reversed().toInt()) > Int.MAX_VALUE) Int.MAX_VALUE else
            (s1.reversed().toInt() + s2.reversed().toInt())}".reversed()
    }catch ( e:Exception){
        e.printStackTrace()
        result = "2147483647"
    }
    var returnList = ListNode(decimalDigitValue(result[0]))
    var head = returnList
    for(index in 1 until result.length){
        var temp = ListNode(decimalDigitValue(result[index]))
        returnList.next = temp
        returnList = temp
    }
    return head
}

//非递归实现
fun climbStairs2(n: Int): Int {//
    if(n==1||n==2||n==3) return n
    var way = 0
    var n1= 3
    var n2 = 2
    for(index in 3 until n){
        if(index%2==0){
            n1 += n2
        }else{
            n2 += n1
        }
    }
    way = if(n1>n2) n1 else n2
    return way
}

tailrec fun climbStairs(n: Int): Int {//
    var way = 0
    //分析  这是一个反推问题，如果要知道n阶楼梯有多少种走法  其实就是n-1阶走法数量加个n-2的数量  F(n)=F(n-1)+F(n-2)  1:1 2:2 3:3 4:5  斐波那契数列
    if(n==1||n==2||n==3){
        return n
    }else{
        way+=climbStairs(n-1)+climbStairs(n-2)
    }
    return way
}

fun mySqrt(x: Int): Int {
    if(x==0||x==1) return x
    if(x>2147395600)return 46340
    var value1 = 0
    var value2 = 0
    var count = 0
    when(x){
        in 100 until 10000 -> count=10
        in 10000 until 1000000 -> count=100
        in 1000000 until 100000000 -> count=1000
        in 100000000 until 10000000000 -> count=10000
    }
    while(count<x){
        value1 = count*count
        value2 = (count+1)*(count+1)
        if((x>=value1)&&(x<value2)){
            return count
        }else{
            count++
        }
    }
    return count
}

fun addBinary(a: String, b: String): String {
    var mA = a
    var mB = b
    var size = if (mA.length > mB.length) mA.length else mB.length
    if (mA.length < size) {
        for (index in 0 until size - mA.length) {
            mA = "0" + mA
        }
    }
    if (mB.length < size) {
        for (index in 0 until size - mB.length) {
            mB = "0" + mB
        }
    }
    var arrayA = mA.toCharArray()
    var arrayB = mB.toCharArray()
    var result = ""
    var temp = 0
    for (indexA in size-1 downTo 0) {
        if (arrayA[indexA] == '1' && arrayB[indexA] == '1') {//两个1
            if (temp == 1) {
                result = "1" + result
                temp = 1
            } else {
                result = "0" + result
                temp = 1
            }
        } else if (arrayA[indexA] == '0' && arrayB[indexA] == '0') {//两个0
            if (temp == 1) {
                result = "1" + result
            } else {
                result = "0" + result
            }
            temp = 0
        } else {//一个1一个0
            if (temp == 1) {
                result = "0" + result
                temp = 1
            } else {
                result = "1" + result
                temp = 0
            }
        }
    }
    if(temp==1) result = "1" + result
    return result
}

fun lengthOfLastWord(s: String): Int {
    if(s.isEmpty())return 0
    if(s.length==1&&s[0]!=' ')return 1
    var count = 0
    for(index in s.length-1 downTo 0){
        if(s[index]!=' '){
            count++
        }else{
            if(count>0){
                return count
            }
        }
    }
    return count
}

fun maxSubArray(nums: IntArray): Int {
    if(nums.size==1)return nums[0]
    if(nums==null|| nums.isEmpty())return 0
    var max:Int = nums.max()!!
    var temp:Int = 0
    //暴力求解
//    for(count in 0 until nums.size){
//        temp = 0
//        for (index in count until nums.size){
//            temp+=nums[index]
//            if(temp>max){
//                max = temp
//            }
//        }
//    }
    //累加
    for(count in 0 until nums.size){
        temp+=nums[count]
        if(nums[count]>temp){
            temp = nums[count]
        }else{
            if(max<temp)
                max = temp
        }
    }
    return max
}

fun searchInsert(nums: IntArray, target: Int): Int {
    if(target<=nums[0])return 0
    if(nums.size<5){
        for (index in 0 until nums.size){
            if(nums[index] == target||nums[index]>target) return index
        }
    }else{
        //上二分
        var tempArray = nums
        var index = 0
        var trueIndex = index
        while (index<tempArray.size){
            index = Math.floor((tempArray.size/2).toDouble()).toInt()
            if(tempArray[index]==target){
                return index+trueIndex
            } else if(tempArray[index]<target){
                tempArray = tempArray.asList().subList(index,tempArray.size).toIntArray()
                trueIndex+=index
                if(tempArray.size==1) return index+trueIndex
                index=0
            }else{
                tempArray = tempArray.asList().subList(0,index).toIntArray()
                if(tempArray.size==1) return index+trueIndex
                index=0
            }
        }
        return 0
    }
    return nums.size
}

fun strStr(haystack: String, needle: String): Int {
    if(haystack==needle) return 0
    if(haystack=="")return -1
    if(haystack.length<needle.length)return -1
    if(needle=="")return 0
    val target = needle.toCharArray()
    var rightCount = 0
    for(i in 0 until haystack.length){
        if(haystack[i]==target[0]){
            for(j in 1 until needle.length){
                if(i+j>haystack.length-1) return -1
                if(haystack[i+j] != needle[j]){
                    rightCount = 0
                    break
                }else{
                    rightCount++
                }
            }
            if(rightCount == needle.length-1){
                return i
            }
        }
    }
    return -1
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    var count = -1
    var result : Array<Int> = Array(nums.size, {0})
    for (indes in 0 until nums.size){
        if(nums[indes]!=`val`){
            count++
            result[count] = nums[indes]
        }
    }
    for(indes in 0 until result.size){
        nums[indes] = result[indes]
    }
    return count+1
}

fun reverse(x: Int): Int {
    if(x<Int.MIN_VALUE||x>Int.MAX_VALUE){
        return 0
    }
    val start:String
    var isFu:Boolean = false
    if(x<0){
        isFu = true
        start = x.toString().replace("-","")
    }else{
        start = x.toString()
    }
    val lenght = start.length
    var stack = ArrayList<Char>()
    for(i in 0..lenght-1){
        stack.add(start[i])
    }
    val end = StringBuilder()
    for(j in 0..lenght-1){
        end.append(stack.removeAt(stack.size-1))
    }
    try {
        if(isFu) {
            return -end.toString().toInt()
        }else {
            return end.toString().toInt()
        }
    }catch (e:Exception){
        e.printStackTrace()
        return 0
    }
}

fun longestCommonPrefix(strs: Array<String>): String {
    if(strs==null||strs.size<=0) return ""
    var max = strs.maxBy { it.length }
    var min = strs.minBy { it.length }
    if(max==""||min=="") return ""
    if(strs.size==1){
        return strs[0]
    }
    val s1 = strs[0]
    var builder = StringBuilder()
    var stringPool:MutableList<String> = mutableListOf()
    for(i in 0 until s1.length){
        builder.append(s1[i])
        for(j in 1 until strs.size){
            //.substring(i,strs[j].length)
            if(strs[j].length<=i) {
                builder.replace(builder.length-1,builder.length,"")
                stringPool.add(builder.toString())
                builder.replace(0,builder.length,"")
                break
            }
            var tempString = strs[j]
            val tooStort = tempString.length<i+1
            val targetString = tempString.substring(i,i+1)
            val cutString = if(builder.toString().length==1)
                builder.toString() else builder.toString().substring(builder.toString().length-1,builder.toString().length)
            if(tooStort|| targetString != cutString){
                builder.replace(builder.length-1,builder.length,"")
                stringPool.add(builder.toString())
                builder.replace(0,builder.length,"")
                break
            }
        }
    }
    stringPool.add(builder.toString())
    var s = stringPool.maxBy { it.length }
    if(s == strs[strs.size-1].substring(strs[strs.size-1].length-1,strs[strs.size-1].length)){
        if(max.toString().length==min.toString().length) {
            return if (max == s) s ?: "" else ""
        }else{
            if(strs[0].startsWith(s)){
                return s
            }else{
                return ""
            }
        }
    }
    return s ?: ""
}

fun isValid(s: String): Boolean {
    var index:Int = 0
    while (s.length>index){
        val char:Char = s[index]
        var nextChar:Char = ' '
        when(char){
            '(' -> nextChar=')'
            '{' -> nextChar='}'
            '[' -> nextChar=']'
            ' ' -> nextChar=' '
        }
        val next = s.indexOf(nextChar,index,false)
        if(next<0){
            return false
        }
        val nexts = s.substring(index+1,next)
        index = next+1
        if(nexts.length%2!=0){
            return false
        }else{
            if(nexts==""){
                if(s.length>index){
                    continue
                }else{
                    return true
                }
            }
            return isValid(nexts)
        }
    }
    return true
}

class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}