# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: textmessage.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='textmessage.proto',
  package='',
  syntax='proto3',
  serialized_options=None,
  serialized_pb=_b('\n\x11textmessage.proto\"\x1b\n\x0bTextMessage\x12\x0c\n\x04text\x18\x01 \x01(\tb\x06proto3')
)




_TEXTMESSAGE = _descriptor.Descriptor(
  name='TextMessage',
  full_name='TextMessage',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='text', full_name='TextMessage.text', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=21,
  serialized_end=48,
)

DESCRIPTOR.message_types_by_name['TextMessage'] = _TEXTMESSAGE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

TextMessage = _reflection.GeneratedProtocolMessageType('TextMessage', (_message.Message,), dict(
  DESCRIPTOR = _TEXTMESSAGE,
  __module__ = 'textmessage_pb2'
  # @@protoc_insertion_point(class_scope:TextMessage)
  ))
_sym_db.RegisterMessage(TextMessage)


# @@protoc_insertion_point(module_scope)
