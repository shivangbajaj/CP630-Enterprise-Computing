//local vector and matrix operations

import org.apache.spark.mllib.linalg.{Vectors,Vector}
val dv1 = Vectors.dense(5.0,6.0,7.0,8.0)
val dv2 = Vectors.dense(Array(5.0,6.0,7.0,8.0))
val sv = Vectors.sparse(4, Array(0,1,2,3), Array(5.0,6.0,7.0,8.0))
dv2(2)
dv1.size
dv2.toArray

import org.apache.spark.mllib.linalg.{DenseVector, SparseVector, Vector}
import breeze.linalg.{DenseVector => BDV,SparseVector => BSV,Vector => BV}
def toBreezeV(v:Vector):BV[Double] = v match {
case dv:DenseVector => new BDV(dv.values)
case sv:SparseVector => new BSV(sv.indices, sv.values, sv.size)
}
toBreezeV(dv1) + toBreezeV(dv2)
toBreezeV(dv1).dot(toBreezeV(dv2))


//dense and sparse matrix operations
import org.apache.spark.mllib.linalg.{DenseMatrix, SparseMatrix, Matrix, Matrices}
import breeze.linalg.{DenseMatrix => BDM,CSCMatrix => BSM,Matrix => BM}
val dm = Matrices.dense(2,3,Array(5.0,0.0,0.0,3.0,1.0,4.0))
dm(1,1)
dm.transpose

val sm = Matrices.sparse(2,3,Array(0,1,2,4), Array(0,1,0,1), Array(5,3,1,4))
sm(1,1)
sm.transpose




