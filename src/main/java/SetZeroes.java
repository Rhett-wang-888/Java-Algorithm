/**
 * @author Rhett
 * @title: SetZeroes
 * @description: TODO 设置矩阵 如果有0，那么以此为中心的列和行都是0.
 * @date 2021/3/21 10:25
 */
public class SetZeroes {

    public static void main(String[] args) {

    }
    public static void setZerose(int[][] matrix){
        int m=matrix.length,n=matrix[0].length;
        boolean[] row=new boolean[m];
        boolean[] clo=new boolean[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row[i]=clo[j]=true;
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(row[i]||clo[j])
                matrix[i][j]=0;
            }
        }
    }

    public static void setZerose1(int[][] matrix){
        int m=matrix.length,n=matrix[0].length;
        boolean flagCol0=false,flagRow0=false;
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0)
                flagCol0=true;
        }
        for(int j=0;j<n;j++){
            if(matrix[0][j]==0)
                flagRow0=true;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0)
                    matrix[i][0]=matrix[0][j]=0;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][0]==0||matrix[0][i]==0)
                    matrix[i][j]=0;
            }
        }
        if(flagCol0){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }
        if(flagRow0){
            for(int j=0;j<n;j++){
                matrix[0][j]=0;
            }
        }
    }
}
