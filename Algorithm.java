public class Algorithm{
    private Algorithm algorithm; 
    private Algorithm(){}
    public Algorithm getAlgorithm(){
        if(this.algorithm==null){
            this.algorithm = new Algorithm();
            return this.algorithm;
        }else{
            return this.algorithm;
        }
    }


    public static void reverse(int[] arr){
        int len = arr.length;
        for(int i =0;i<len/2;i++){
            int temp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = temp;
        }
    }

    public static int halfSearch(int[] arr,int begin,int end,int target){
        if(target>arr[end]||begin>end)
            return -1;
        int mid = (end + begin)/2;
        if(target==arr[mid])
            return mid;
        if(target>arr[mid]){
            return halfSearch(arr,mid+1, end, target);
        }
        if(target<arr[mid]){
            return halfSearch(arr, begin,mid-1, target);
        }
        return -1;
    }

    public static void QuickSort(int [] array,int left,int right){
        int i = left;
        int j = right;
        if(left>right)
            return;
        int temp = array[left];
        while(left!=right){
            while(left<right&&array[right]>temp){
                right--;
            }
            array[left] = array[right];
            while(left<right&&array[left]<=temp){
                left++;
            }
            array[right] = array[left];           
        }
        array[left] = temp;
        QuickSort(array, i, left-1);
        QuickSort(array, left+1, j);

    }

    public static void MaoPaoSort(int [] array){
        int len = array.length;
        for(int i =0;i<len;i++){
            for(int j = 0;j<len-1;j++){
                if(array[j]>array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] a = new int[]{11,22,33,44,55,66};
        Algorithm.reverse(a);
        for(int e:a)
            System.out.print(e+" ");
    }
}