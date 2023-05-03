package com.example.smartqueryresolver;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{


    final List<Message> messageList;
    public MessageAdapter (List<Message> messageList) {
        this.messageList = messageList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, null);
        return new MyViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = messageList.get(position);
        if(message.getSentBy().equals(Message.SENT_BY_ME)) {
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(message.getMessage());
        } else {
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.rightChatView.setVisibility(View.GONE);
            holder.leftTextView.setText(message.getMessage());
        }

        holder.leftTextView.setOnLongClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("message", message.getMessage());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(view.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            return true;
        });

        holder.rightTextView.setOnLongClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("message", message.getMessage());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(view.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final LinearLayout leftChatView;
        final LinearLayout rightChatView;
        final TextView leftTextView;
        final TextView rightTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leftChatView = itemView.findViewById(R.id.leftChatView);
            rightChatView = itemView.findViewById(R.id.rightChatView);
            leftTextView = itemView.findViewById(R.id.leftChatTextView);
            rightTextView = itemView.findViewById(R.id.rightChatTextView);
        }
    }
}
