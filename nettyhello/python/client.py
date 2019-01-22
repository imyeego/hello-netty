import socket
from user_pb2 import *
from demo_pb2 import Frame
from textmessage_pb2 import *
import json
from bitstring import BitStream

login = {
    'version' : 1,
    'command' : 1,
    'username' : 'Jay Chou',
    'password' : 'imy32592186'
}

MAGIC_NUM = 305419896

# user = User()
# user.id = 3
# user.name = 'liuzhao'
#
# message = TextMessage()
# message.text = 'hello'

def decode(data):
    stream = BitStream(data)
    stream.read('int:32')
    stream.read('int:8')
    stream.read('int:8')
    n = stream.read('int:32')
    fmt = 'bytes:%d' % n
    user = json.loads(stream.read(fmt).decode())
    return repr(user)

def encode(data):
    stream = BitStream()
    stream.append('int:32=%d' % MAGIC_NUM)
    stream.append('int:8=%d' % data['version'])
    stream.append('int:8=%d' % data['command'])
    json_bytes = json.dumps(data).encode()
    stream.append('int:32=%d' % len(json_bytes))
    stream.append(json_bytes)
    return stream.bytes

def encodeToProtobuf(data):
    frame = Frame()
    frame.messageType = data.__class__.__name__
    frame.payload = data.SerializeToString()
    return frame.SerializeToString()

def decodeFromProtobuf(frame):
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
    s.send(encode(login))

    while True:
        data = s.recv(1024)
        dataStr = decode(data)
        print(dataStr)
    s.close()



if __name__ == "__main__":
    host = '127.0.0.1'
    port = 9999
    connect(host, port)
    