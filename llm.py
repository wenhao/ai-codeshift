import os

from dotenv import load_dotenv
from langchain_core.messages import HumanMessage, SystemMessage
from langchain_openai import ChatOpenAI

load_dotenv()

base_url = os.getenv("AI_PROVIDER")
api_key = os.getenv("AI_API_KEY")
model = os.getenv("AI_MODEL")

llm = ChatOpenAI(
    api_key=api_key,
    base_url=base_url,
    model=model,
    temperature=0.7
)

if __name__ == "__main__":
    messages = [
        SystemMessage(content="You are a helpful assistant."),
        HumanMessage(content="你是谁？")
    ]
    response = llm.invoke(messages)
    print("模型回答：", response.content)
    print("\n完整响应：", response.model_dump_json())
