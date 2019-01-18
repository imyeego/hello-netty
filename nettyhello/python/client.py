import socket
from user_pb2 import *
from demo_pb2 import Frame
from textmessage_pb2 import *

user = User()
user.id = 3
user.name = 'liuzhao'

message = TextMessage()
message.text = 'hello'

def encode(data):
    frame = Frame()
    frame.messageType = data.__class__.__name__
    frame.payload = data.SerializeToString()
    return frame.SerializeToString()

def decode(frame):
    frameStr = Frame()
    frameStr.ParseFromString(frame)
    messageType = frameStr.messageType
    moudel_pb2 = __import__(messageType.lower() + '_pb2')
    class_name = getattr(moudel_pb2, messageType)
    userStr = class_name()
    userStr.ParseFromString(frameStr.payload)
    return userStr

def connect(host, port):
    s = socket.socket()
    s.connect((host, port))
    s.send(encode(message))

    while True:
        data = s.recv(1024)
        dataStr = decode(data)
        print(dataStr)
    s.close()



if __name__ == "__main__":
    host = '127.0.0.1'
    port = 9999
    connect(host, port)
    