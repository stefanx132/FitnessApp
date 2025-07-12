package com.example.fitnessapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessapp.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatBotFragment extends Fragment {
    private final String AIML_API_KEY = "853bb58bfaa1495e8830a065c78447aa";
    private EditText ingredientsEditText;
    private Button ingredientsBtn;
    private RecyclerView recipeRecyclerView;
    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();
    private static final String TAG = "ChatBotAI";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatbot_ai, container, false);

        ingredientsEditText = view.findViewById(R.id.ingredients_edit_text);
        ingredientsBtn = view.findViewById(R.id.get_recipe_btn);
        recipeRecyclerView = view.findViewById(R.id.recipe_recycler_view);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MessageAdapter(messages);
        recipeRecyclerView.setAdapter(adapter);

        ingredientsBtn.setOnClickListener(v -> {
            String userText = ingredientsEditText.getText().toString().trim();
            if (!userText.isEmpty()) {
                ingredientsEditText.setText("");
                ingredientsEditText.setHint("Ask something...");

                // User message
                messages.add(new Message(userText, true));
                adapter.notifyItemInserted(messages.size() - 1);
                recipeRecyclerView.scrollToPosition(messages.size() - 1);

                // Bot message
                Message thinking = new Message("Thinking...", false);
                messages.add(thinking);
                int botIndex = messages.size() - 1;
                adapter.notifyItemInserted(botIndex);
                recipeRecyclerView.scrollToPosition(botIndex);

                getRecipeFromAI(userText, botIndex);
            } else {
                messages.add(new Message("Ask something.", false));
                adapter.notifyItemInserted(messages.size() - 1);
                recipeRecyclerView.scrollToPosition(messages.size() - 1);
                Log.d(TAG, "No input provided.");
            }
        });
        return view;
    }

    private void getRecipeFromAI(String prompt, int botIndex) {
        OkHttpClient client = new OkHttpClient();

        String json = "{ \"model\": \"gpt-4o\", " +
                "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

        Log.d(TAG, "Sending request with JSON: " + json);

        Request request = new Request.Builder()
                .url("https://api.aimlapi.com/v1/chat/completions") // consider updating to correct endpoint if needed
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + AIML_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Network request failed", e);
                requireActivity().runOnUiThread(() -> {
                    messages.set(botIndex, new Message("Something went wrong. Please try again.", false));
                    adapter.notifyItemChanged(botIndex);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "HTTP Response code: " + response.code());
                Log.d(TAG, "HTTP Response message: " + response.message());

                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.d(TAG, "Raw JSON response: " + res);

                        JSONObject jsonObject = new JSONObject(res);
                        String reply = jsonObject
                                .getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");

                        Log.d(TAG, "Extracted reply: " + reply);

                        requireActivity().runOnUiThread(() -> animateBotReply(reply, botIndex));
                    } catch (Exception e) {
                        Log.e(TAG, "JSON parsing error: " + e.getMessage(), e);
                        requireActivity().runOnUiThread(() -> {
                            messages.set(botIndex, new Message("Invalid bot reply format.", false));
                            adapter.notifyItemChanged(botIndex);
                        });
                    }
                } else {
                    String errorBody = response.body() != null ? response.body().string() : "no body";
                    Log.e(TAG, "API call failed with code " + response.code() + ": " + response.message());
                    Log.e(TAG, "Error body: " + errorBody);

                    requireActivity().runOnUiThread(() -> {
                        messages.set(botIndex, new Message("Bot error " + response.code() + ": " + response.message(), false));
                        adapter.notifyItemChanged(botIndex);
                    });
                }
            }
        });
    }

    private void animateBotReply(String reply, int indexToReplace) {
        messages.remove(indexToReplace);
        adapter.notifyItemRemoved(indexToReplace);

        // Empty bot message for animation
        Message botMessage = new Message("", false);
        messages.add(botMessage);
        int newIndex = messages.size() - 1;
        adapter.notifyItemInserted(newIndex);

        new Thread(() -> {
            StringBuilder currentText = new StringBuilder();
            for (char c : reply.toCharArray()) {
                currentText.append(c);
                String partial = currentText.toString();

                updateBotMessage(partial, newIndex);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateBotMessage(String text, int index) {
        requireActivity().runOnUiThread(() -> {
            messages.get(index).text = text;
            adapter.notifyItemChanged(index);
            recipeRecyclerView.scrollToPosition(index);
        });
    }

}