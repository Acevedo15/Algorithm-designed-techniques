public class AlgoritmoSalto {
    public static int salto(int[] arr, int x) {

        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = step;
        while (arr[Math.min(step, n) -1] <= x && step <= n) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n)
                prev = n-1;
        }

        while (prev >= 0 && arr[prev] > x) {
            prev--;
            if (prev >= 0 && arr[prev] == x)
                return prev;
        }

        if (prev >= 0 && prev < n && arr[prev] == x)
            return prev;
        else
            return -1;
    }

}
