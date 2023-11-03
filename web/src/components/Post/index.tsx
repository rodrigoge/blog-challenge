import { MessageCircle, Trash, Pencil } from 'lucide-react';

export default function Post() {
  return (
    <div className="w-6/12 h-auto flex justify-between border rounded-lg border-gray-300 p-5">
      <div className='overflow-ellipsis whitespace-nowrap overflow-hidden'>
        <h1 className="font-bold">
          Post title
        </h1>
        <p className='overflow-ellipsis whitespace-normal overflow-hidden'>
          Description of first post in the application using a big large text for analyzing css responsability.
        </p>
        <div className='flex flex-row'>
          <button className='flex mt-4 text-zinc-400 hover:text-zinc-500 cursor-pointer'>
            <MessageCircle size={20} />
            <p className="text-sm ml-1 overflow-ellipsis whitespace-nowrap overflow-hidden">20 commentaries</p>
          </button>
          <button className='flex mt-4 ml-8 text-zinc-400 hover:text-zinc-500 cursor-pointer'>
            <Pencil size={20} />
            <p className="text-sm ml-1 overflow-ellipsis whitespace-nowrap overflow-hidden">Edit this post</p>
          </button>
        </div>
      </div>
      <div>
        <button>
          <Trash size={20} className='text-red-500 hover:text-red-600 cursor-pointer' />
        </button>
      </div>
    </div>
  );
}