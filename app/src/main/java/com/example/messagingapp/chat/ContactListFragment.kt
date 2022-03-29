package com.example.messagingapp.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.messagingapp.R
import com.example.messagingapp.domain.Contact

/**
 * A simple [Fragment] subclass.
 */
class ContactListFragment : Fragment(R.layout.fragment_contact_list) {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvContacts)
        recyclerView.adapter = ContactAdapter(
            listOf(
                Contact("Matt", "9733301048"),
                Contact("Jeff", "9733301048"),
                Contact("Wynton", "9733301048"),
                Contact("James", "9733301048"),
                Contact("Myles", "9733301048"),
                Contact("B mans", "9733301048"),
                Contact("Bucco", "9733301048"),
                Contact("Dillon", "9733301048"),
                Contact("Cello", "9733301048"),
                Contact("Dewey", "9733301048"),
                Contact("Charlie", "9733301048"),
            )
        ) { contactView -> viewModel.setActiveChat(contactView as TextView) }

        subscribeToChatState()
    }

    private fun subscribeToChatState() {
        viewModel.chatState.observe(this.viewLifecycleOwner) { chatState ->
            when (chatState) {
                is ChatState.ContactSelected -> // fragment transaction to chat fragment...
            }
        }
    }
}
