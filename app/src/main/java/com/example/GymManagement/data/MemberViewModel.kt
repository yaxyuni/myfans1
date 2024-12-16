package com.example.GymManagement.data

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.GymManagement.models.Member
import com.example.GymManagement.navigation.ROUTE_VIEW_MEMBER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MemberViewModel(): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean>get()= _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage:LiveData<String>get()= _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage:LiveData<String>get()= _successMessage

    fun saveMember( firstname: String, lastname:String,
                   gender:String, age:String,
                    health:String,
                   context: Context){
        val id = System.currentTimeMillis().toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("Member/$id")
       val memberData = Member(firstname,lastname,gender,health,age,id)

        _isLoading.value = true

        dbRef.setValue(memberData).addOnCompleteListener{ task->
            if (task.isSuccessful){
              showToast("Members added successfully",context)

            }else{
                Toast.makeText(context,"Members not added successfully",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    fun viewMember(member: MutableState<Member>,
                    members: SnapshotStateList<Member>, context: Context):
            SnapshotStateList<Member>{
        val ref = FirebaseDatabase.getInstance().getReference()
            .child("Member")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                members.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Member::class.java)
                    member.value = value!!
                    members.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Failed to fetch members", context)
            }
        })
        return members
    }
    fun updateMember(context: Context,navController: NavController,
                     firstname: String,lastname: String,
                     gender: String,health: String,age: String,id: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Member/$id")
        val updatedMember = Member("",firstname, lastname,
            gender,health,age)

        databaseReference.setValue(updatedMember)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    showToast("Member Updated Successfully",context)
                    navController.navigate(ROUTE_VIEW_MEMBER)
                }else{
                    showToast("Record update failed", context)
                }
            }
    }


    fun deleteMember (context: Context,id: String,navController: NavController){
        AlertDialog.Builder(context)
            .setTitle("Delete the Member ")
            .setMessage("Are you sure you want to delete this member ?")
            .setPositiveButton("yes"){ _, _->
              val databaseReference = FirebaseDatabase.getInstance()
                  .getReference("Member/$id")
                databaseReference.removeValue().addOnCompleteListener {
                    task->
                    if(task.isSuccessful){
                        showToast("Member Deleted successfully",context)
                    }else{
                        showToast("Member not Deleted",context)
                    }

                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }

            .show()
    }


    fun showToast(message: String, context: Context){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}

    }
