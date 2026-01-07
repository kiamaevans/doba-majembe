package com.example.doba_majembe.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.doba_majembe.models.UserModel
import com.example.doba_majembe.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    private fun saveUserToFirestore(
        user: UserModel,
        context: Context,
        onSuccess: () -> Unit
    ) {
        firestore.collection("Users")
            .document(user.userId)
            .set(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    Toast.makeText(
                        context,
                        task.exception?.message ?: "Failed to save user",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
    // 🚨 User Signup
    fun signupUser(
        username: String,
        fullname: String,
        email: String,
        password: String,
        confirmPassword: String,
        navController: NavController,
        context: Context
    ) {
        if (username.isBlank() || email.isBlank() ||
            password.isBlank() || confirmPassword.isBlank()
        ) {
            Toast.makeText(context, "Please fill all the fields!", Toast.LENGTH_LONG).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val user = UserModel(
                        userId = userId,
                        username = username, // or fullname if you prefer
                        email = email,
                        role = "User"

                    )

                    saveUserToFirestore(user, context) {
                        Toast.makeText(context, "User registered successfully", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        task.exception?.message ?: "Registration failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
    fun firebaseAuthWithGoogle(
        idToken: String,
        navController: NavController,
        context: Context
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val firebaseUser = auth.currentUser!!
                    val userId = firebaseUser.uid

                    val user = UserModel(
                        userId = userId,
                        username = firebaseUser.displayName ?: "User",
                        email = firebaseUser.email ?: "",
                        role = "User"
                    )

                    // Save ONLY if user does not exist
                    firestore.collection("Users")
                        .document(userId)
                        .get()
                        .addOnSuccessListener { document ->
                            if (!document.exists()) {
                                saveUserToFirestore(user, context) {
                                    Toast.makeText(
                                        context,
                                        "Signed in with Google",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate(ROUTE_LOGIN)
                                }
                            } else {
                                navController.navigate(ROUTE_LOGIN)
                            }
                        }

                } else {
                    Toast.makeText(
                        context,
                        task.exception?.message ?: "Google sign-in failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


}